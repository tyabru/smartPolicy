   // 判断文本编码格式
   export async function isGb (file) {
    return await new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsText(file,'GB2312');
      console.log(file)
  
      reader.onloadend = (e) => {
        const str = e.target.result;
        // 大致取一半
        const sampleStr = str.slice(4, 4 + str.length / 2);
        if (sampleStr.indexOf("�") === -1) {
          resolve(true);
        } else {
          reject(new Error(""));
        }
      };
  
      reader.onerror = () => {
        reject(new Error("文件内容读取失败，请检查文件是否损坏"));
      };
    });
  };
