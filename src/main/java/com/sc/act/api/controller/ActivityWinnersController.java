package com.sc.act.api.controller;

import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.BaseRuntimeException;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.commons.web.constant.CommonConstant;
import com.sc.act.api.commons.web.enums.ResultEnum;
import com.sc.act.api.commons.web.util.ResourceUtil;
import com.sc.act.api.model.bo.ExcelWinnersInfoBmo;
import com.sc.act.api.service.ActivityWinnersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 功能描述: 活动中奖名控制类
 *
 * @className:ActivityWinnersController
 * @projectName:
 * @author: generater-code
 * @date: 2019-11-17 18:34:48
 */
@RestController
@RequestMapping(value = "/mis/activity/winners")
@Api(value = "活动中奖名控制类", tags = "活动中奖名控制类")
public class ActivityWinnersController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ActivityWinnersController.class);

    @Autowired
    private ActivityWinnersService activityWinnersService;

    @ApiOperation(value = "中奖名单模板下载")
    @GetMapping("/download/excel")
    public void downloadExcel(HttpServletResponse response) {
        LOG.info("中奖名单模板下载");
        InputStream inputStream = null;
        OutputStream out = null;
        try {
            //获取要下载的模板名称
            String fileName = "winners_list_template_" + System.currentTimeMillis() + ".xlsx";
            LOG.info("中奖名单模板下载模板名称{}", fileName);
            //设置要下载的文件的名称
            response.setHeader("Content-disposition", "attachment;fileName=" + fileName);
            //通知客服文件的MIME类型
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //获取文件的路径
            inputStream = ResourceUtil.asStream("templates/winners_list_template.xlsx");

            out = response.getOutputStream();
            byte[] b = new byte[2048];
            int len;
            while ((len = inputStream.read(b)) != -1) {
                out.write(b, 0, len);
            }

        } catch (IOException iex) {
            LOG.error("中奖名单模板下载获取资源异常", iex);

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.error("中奖名单模板下载关闭输入流异常", e);
                }
            }

            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    LOG.error("中奖名单模板下载关闭输出流异常", e);
                }
            }

        }
    }

    /**
     * 上传文件
     */
    @ApiOperation(value = "中奖名单上传")
    @PostMapping(value = "/upload/xlsx")
    public Result<String> uploadSingleFile(MultipartFile file, @RequestHeader("activityId") Integer activityId) {
        LOG.info("中奖名单上传");
        // 获取文件名
        if (null == activityId) {
            throw new BaseRuntimeException("1001", "活动ID不能为空");
        }

        String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            throw new BaseRuntimeException("1001", "中奖名单上传未获取到文件名");
        }
        LOG.info("中奖名单上传文件名{}", fileName);
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(CommonConstant.EXCEL_FILENAME_COMMA));
        if (!suffix.toLowerCase().contains(CommonConstant.EXCEL_XLS) && !suffix.toLowerCase().contains(CommonConstant.EXCEL_XLSX)) {
            throw new BaseRuntimeException("1001", "中奖名单上传文件格式异常，请上传Excel文件格式");
        }

        InputStream streamToUploadFrom = null;
        BufferedInputStream bufferedInputStream = null;

        try {


            Result<String> result = new Result<>();
            try {
                streamToUploadFrom = file.getInputStream();
            } catch (IOException e) {
                throw new BaseRuntimeException("1001", "中奖名单上传获取文件流失败");
            }
            if (streamToUploadFrom == null) {
                throw new BaseRuntimeException("1001", "中奖名单上传未获取到文件流");
            }

            bufferedInputStream = new BufferedInputStream(streamToUploadFrom);


            //由于2003和2007的版本所使用的接口不一样，所以这里统一用Workbook做兼容
            boolean isExcel2003 = suffix.toLowerCase().endsWith(CommonConstant.EXCEL_XLS);
            Workbook workbook = null;
            try {
                if (isExcel2003) {
                    workbook = new HSSFWorkbook(bufferedInputStream);
                } else {
                    workbook = new XSSFWorkbook(bufferedInputStream);
                }
            } catch (IOException e) {
                throw new BaseRuntimeException("1001", "中奖名单上传创建输入流出错");
            }

            //Excel表中的内容

            Sheet sheet = workbook.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum();
            //这里重1开始，跳过了标题，直接从第二行开始解析
            lastRowNum = lastRowNum + 1;
            if (lastRowNum > CommonConstant.EXCEL_ROW_NUM) {
                throw new BaseRuntimeException("1001", "中奖名单上传文件超过了50条");
            }

            List<String> listFail = new ArrayList<>();
            List<ExcelWinnersInfoBmo> listSuccess = new ArrayList<>();

            for (int i = 1; i < lastRowNum; i++) {
                Row row = sheet.getRow(i);
                //设置行格式和验证start 这里最好做成一个方法，免得代码多处复制

                if (null == row.getCell(0)
                        || null == row.getCell(1)
                        || null == row.getCell(2)
                        || null == row.getCell(3)
                        || null == row.getCell(4)
                        || null == row.getCell(5)
                ) {

                    continue;
                }

                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);

                String serNo = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String moblie = row.getCell(2).getStringCellValue();
                String bankNo = row.getCell(3).getStringCellValue();
                String bankName = row.getCell(4).getStringCellValue();
                String price = row.getCell(5).getStringCellValue();

                if (StringUtils.isBlank(serNo)
                        || StringUtils.isBlank(name)
                        || StringUtils.isBlank(moblie)
                        || StringUtils.isBlank(bankNo)
                        || StringUtils.isBlank(bankName)
                        || StringUtils.isBlank(price)
                ) {
                    listFail.add(serNo);
                    continue;
                }

                if (!Pattern.matches("^\\d+$", serNo)
                        || !Pattern.matches("^1[3-9]\\d{9}$", moblie)
                        || !Pattern.matches("^\\d+$", price)) {
                    listFail.add(serNo);
                    continue;
                }

                ExcelWinnersInfoBmo excelWinnersInfoBmo = new ExcelWinnersInfoBmo();
                Integer integer = Integer.valueOf(price);
                excelWinnersInfoBmo.setAwardAmount(integer * 100);
                excelWinnersInfoBmo.setName(name);
                excelWinnersInfoBmo.setMobile(Long.valueOf(moblie));
                excelWinnersInfoBmo.setCardName(name);
                excelWinnersInfoBmo.setBankName(bankName);
                excelWinnersInfoBmo.setCardNumber(bankNo);

                listSuccess.add(excelWinnersInfoBmo);
            }

            if (CollectionUtils.isNotEmpty(listFail)) {
                LOG.error("Excel中的部分内容校验不通过,序号{}", listFail.toString());
                throw new BaseRuntimeException("1001", "Excel中的部分内容校验不通过,序号 " + listFail.toString());
            }

            if (CollectionUtils.isEmpty(listSuccess)) {
                throw new BaseRuntimeException("1001", "Excel中的内容全部校验不通过");
            }

            activityWinnersService.handlerWinnersInfo(listSuccess);

            result.setRetMsg("操作成功");
            return result;

        } catch (Exception ex) {

            LOG.error("中奖名单上传处理失败", ex);
            throw new BaseRuntimeException(ResultEnum.EXCEPTION);

        } finally {


            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (streamToUploadFrom != null) {
                    streamToUploadFrom.close();
                }
            } catch (IOException e) {
                LOG.error("中奖名单上传关闭流失败", e);
            }


        }


    }

}
