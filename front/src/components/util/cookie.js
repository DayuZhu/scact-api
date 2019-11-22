let getCookie = (name) =>
{
    let arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        //console.log(arr[2])
        return arr[2];
        // return (arr[2]);
    } else {
        return false
    }
}

export {
    getCookie
}
