<template>
  <div class="container">
    <div class="box">
      <div class="login">用户登录</div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="" prop="username">
          <el-input v-model="ruleForm.username" autocomplete="off" placeholder="用户名称"
                    clearable></el-input>
        </el-form-item>
        <el-form-item label="" prop="password">
          <el-input type="password" v-model="ruleForm.password" autocomplete="off" placeholder="用户密码"
                    clearable></el-input>
        </el-form-item>
        <el-form-item>
          <div style="margin-bottom: 15px">
            <el-button type="primary" @click="submitForm('ruleForm')">立即登录</el-button>
          </div>
          <div>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </div>
          <router-link tag="span" :to="{path:'register'}">
            <el-button style="margin-top: 10px">跳转到注册</el-button>
          </router-link>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import {AddRoutes} from "@/router";

class LoginStrategy {
  handleLoginSuccess(res, router) {
    throw new Error("This method should be overridden!");
  }
}

class UserLoginStrategy extends LoginStrategy {
  handleLoginSuccess(res, router) {
    router.push({path: "/front"});
  }
}

class AdminLoginStrategy extends LoginStrategy {
  handleLoginSuccess(res, router) {
    router.push({path: "/"});
  }
}

export default {
  data() {
    return {
      ruleForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名称', trigger: 'blur'},
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.ruleForm)
          this.$axios.post("/sysUser/login", this.ruleForm).then(res => {
            console.log(res)
            if (res.code === 200) {
              this.$message({
                message: "登录成功，正在跳转",
                type: "success"
              })
              let userInfo = res.data
              localStorage.setItem("systemUser", JSON.stringify(userInfo))
              localStorage.setItem("systemRoleMenu", JSON.stringify(userInfo.roleMenu))
              AddRoutes()
              // 使用策略模式处理登录成功后的逻辑
              let strategy;
              if (res.data.roleType === "USER") {
                strategy = new UserLoginStrategy();
              } else {
                strategy = new AdminLoginStrategy();
              }
              strategy.handleLoginSuccess(res, this.$router);
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>
<style>
.box .el-form-item__content {
  margin-left: 0 !important;
}

.box .el-button {
  width: 100% !important;
}

</style>
<style scoped>
.container {
  width: 100%;
  height: 100%;
  position: relative;
  background: url("../assets/img.png") 100% 100%;
  background-size: cover;

}

.login {
  text-align: center;
  margin-bottom: 15px;
  font-size: 22px;
  color: #fff;
}

.box {
  overflow: hidden;
  width: 350px;
  padding: 22px 22px 0 22px;
  box-sizing: border-box;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translateY(-50%) translateX(-50%);
  background-color: skyblue;
  border-radius: 5px;
}

.wx {
  width: 150px;
  height: 150px;
  display: block;
  margin: 0 auto;
}
</style>

