<template>
  <div>
    <el-button @click="getDeviceList">点击获取</el-button>
  </div>
</template>

<script>

  import userService from "@/utils/service/UserService";
  import crypto from 'crypto'
  export default {
    name: "",
    data(){
      return {
        username:'admin',
        password:'admin',
      }
    },
    mounted(){
      this.login();
    },
    methods:{
      //登录逻辑
      login(){
        if(this.username!='' && this.password!=''){
          this.toLogin();
        }
      },
      //登录请求
      toLogin(){
        //需要想后端发送的登录参数
        let loginParam = {
          username: this.username,
          password: crypto.createHash('md5').update(this.password, "utf8").digest('hex')
        }
        let that = this;


        this.$axios({
          method: 'get',
          url:"/api/user/login",
          params: loginParam
        }).then(function (res) {
          console.log(res);
          console.log("登录成功");
          if (res.data.code === 0 ) {
            userService.setUser(res.data.data)
          }else{
            that.isLoging = false;
            that.$message({
              showClose: true,
              message: '登录失败，用户名或密码错误',
              type: 'error'
            });
          }
        }).catch(function (error) {
          console.log(error)
          window.clearTimeout(timeoutTask)
          that.$message.error(error.response.data.msg);
          that.isLoging = false;
        });
      },
      getDeviceList: function () {
        this.$axios({
          method: 'get',
          url: `/api/device/query/devices`,
          params: {
            page: 1,
            count: 5
          }
        }).then( (res)=> {
          if (res.data.code === 0) {
            console.log(res)
          }
        }).catch( (error)=> {
          console.error(error);
        });
      },
    }
  }
</script>

<style scoped>

</style>
