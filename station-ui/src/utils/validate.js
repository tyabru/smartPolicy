import { listResident } from '@/api/person/resident'

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUsername(str) {
  const valid_map = ['admin', 'editor']
  return valid_map.indexOf(str.trim()) >= 0
}

/**
 * @param {string} url
 * @returns {Boolean}
 */
export function validURL(url) {
  const reg = /^(https?|ftp):\/\/([a-zA-Z0-9.-]+(:[a-zA-Z0-9.&%$-]+)*@)*((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])){3}|([a-zA-Z0-9-]+\.)*[a-zA-Z0-9-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(:[0-9]+)*(\/($|[a-zA-Z0-9.,?'\\+&%$#=~_-]+))*$/
  return reg.test(url)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validLowerCase(str) {
  const reg = /^[a-z]+$/
  return reg.test(str)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validUpperCase(str) {
  const reg = /^[A-Z]+$/
  return reg.test(str)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function validAlphabets(str) {
  const reg = /^[A-Za-z]+$/
  return reg.test(str)
}

/**
 * @param {string} email
 * @returns {Boolean}
 */
export function validEmail(email) {
  const reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  return reg.test(email)
}

/**
 * @param {string} str
 * @returns {Boolean}
 */
export function isString(str) {
  if (typeof str === 'string' || str instanceof String) {
    return true
  }
  return false
}

/**
 * @param {Array} arg
 * @returns {Boolean}
 */
export function isArray(arg) {
  if (typeof Array.isArray === 'undefined') {
    return Object.prototype.toString.call(arg) === '[object Array]'
  }
  return Array.isArray(arg)
}

//检验身份证号码
export function validPpliceIdCard (rule, value, callback)  {
  if(value && value.includes("**")) {
    callback();
  }
  if(!value || value === '') {
    callback()
  }else {
    //自定义校验身份证rule
    const arr = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
    const drr = ["1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"];
    let sum = 0;
    for (let i = 0; i < 17; i++) {
      const b = parseInt(value.charAt(i));
      sum += b * arr[i];
    }
    const c = sum % 11;
    if (drr[c] != value.charAt(value.length - 1)) {
      callback(new Error("身份证不合规范"));
    } else {
      callback();
    }
  }
}

export function  validPhone (rule, value, callback) {
  if (value.includes("**")) {
   callback()
  }
  if (!value) {
    callback();
    return;
  }
  const regex = /^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[0-9])|(18[0-9])|(19[1,8,9]))\d{8}$/
  if (!regex.test(value)) {
    callback(new Error('请输入正确的手机号'));
  } else {
      callback();
  }
};

export function validIdCodeByType(code, type = 'CN_CARD') {
  if(type === 'CN_CARD') {
    return validCnIdentity(code);
  } else if(type === 'PASS_PORT') {
    return validPassport(code);
  } else {
    console.warn("未知验证身份号码里类型【type】：" + type);
    return false
  }
}

export function validCnIdentity(identityCode) {
  if(!identityCode || identityCode.length < 1) {
    return false
  }
  const pattern = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
  return pattern.test(identityCode);
}

export function validPassword(password) {
  const regex = "(?![0-9A-Z]+$)(?![0-9a-z]+$)(?![a-zA-Z]+$)[0-9A-Za-z@#!%&._+=~?-]{6,18}$";
  return password.match(regex);
}

export function validPassport(passport) {
  if(!passport || passport.length < 1) {
    return false
  }
  // TODO 护照验证规则
  // const pattern = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
  // return pattern.test(identityCode);
  return true
}
