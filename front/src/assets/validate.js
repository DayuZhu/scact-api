//数字
export function number(str) {
    const reg = /^[0-9]+(.[0-9]{2})?$/
    return reg.test(str)
}
