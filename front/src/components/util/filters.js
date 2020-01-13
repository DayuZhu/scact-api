import citys from '../util/citys'

let place = value => {
    let text ='' ;
    if(value === 'playImg'){
        text = '轮播图'
    }else if(value === 'longImg'){
        text = '长图'
    }
    return text
}
let page = value => {
    let text ='' ;
    if(value === 'first'){
        text = '首页'
    }else if(value === 'design'){
        text = '平面主KV'
    }else if(value === 'threeD'){
        text = '3D效果图'
    }else if(value === 'video'){
        text = 'Event视频'
    }else if(value === 'audio'){
        text = 'BGM'
    }else if(value === 'fieldCAD'){
        text = '场地CAD'
    }
    return text
}
let enterStatus = value => {
    let text ='' ;
    if(value === 1){
        text = '待审核'
    }else if(value === 2){
        text = '审核通过'
    }else if(value === 3){
        text = '审核不通过'
    }
    return text
}
export { page, place, enterStatus  }
