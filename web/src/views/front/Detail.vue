<template>
  <div>
    <el-card>
      <h1 style="text-align: center;margin: 15px auto">页面内容</h1>
      <el-divider></el-divider>
      <div style="margin-top: 35px;display: flex;justify-content: space-between">
        <span><i class="el-icon-view"></i>1000人看过</span>
        <span><i class="el-icon-timer"></i>2024-10-10</span>
        <span><i class="el-icon-user-solid"></i>admin</span>
      </div>

      <el-divider></el-divider>
      <!--富文本编辑器 包含一些html等内容-->
      <div v-html="content"></div>

      <!--使用插值表达式来打印参数-->
      <!--            当前的传递的ID参数为：{{id}}-->
    </el-card>
  </div>
</template>
<script>
export default {
  data(){
    return {
      //获取url传递的id参数
      id: this.$route.query.id,
      content:"<p style='font-weight: bold'>这里是p标签</p>"
    }
  }
}
</script>
