const CryptoJS = require('crypto-js');  //引用AES源码js
const crypto = require('crypto');

export function Decrypt(word) {
    if(!word){
        return word;
    }
    var dc = crypto.createDecipheriv("aes-128-ecb", convertCryptKey("mXG2CGtBkb80LGX/gyyl4A=="), "");
    var decrypted = dc.update(word, 'hex', 'utf8') + dc.final('utf8');
    return decrypted;
}

export function DecryptByPass(word, password) {
  if(!word){
    return word;
  }
  var dc = crypto.createDecipheriv("aes-128-ecb", convertCryptKey(password), "");
  return dc.update(word, 'hex', 'utf8') + dc.final('utf8');
}

export function Encrypt(word) {

    var c = crypto.createCipheriv("aes-128-ecb", convertCryptKey("mXG2CGtBkb80LGX/gyyl4A=="), "");
    var crypted = c.update(word, 'utf8', 'hex') + c.final('hex');
    return crypted.toUpperCase();
}

function convertCryptKey(strKey) {
    var newKey = new Buffer([0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]);
    strKey = new Buffer(strKey);
    for(var i=0;i<strKey.length;i++) newKey[i%16]^=strKey[i];
    return newKey;
}
