<template>
  <div>
    <!--        Row 组件 提供 gutter 属性来指定每一栏之间的间隔，默认间隔为 0。-->
    <el-row :gutter="10">
      <el-col :span="19">
        <div>
          <el-divider content-position="left">最新资讯</el-divider>
          <el-card v-for="item in 5" :key="item"
                   style="margin-bottom: 10px;cursor: pointer">
            <div style="display: flex"  @click="$router.push('/front/detail?id=' + item)">
              <div style="width: 170px;margin-right: 20px">
                <img src="../../assets/register.png" alt=""
                     style="width: 170px;height: 110px;">
              </div>
              <div style="flex: 1">
                <div style="font-size: 17px;text-align: center">标题标题标题标题</div>
                <div style="line-height: 30px">
                  内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容
                </div>
                <div style="margin-top: 5px;display: flex;justify-content: space-between">
                  <span><i class="el-icon-view">1人看过</i></span>
                  <span><i class="el-icon-time">2024-10-10</i></span>
                  <span><i class="el-icon-user-solid">admin</i></span>
                </div>
              </div>
            </div>

          </el-card>
        </div>
      </el-col>
      <el-col :span="5">
        <el-divider content-position="left">友情链接</el-divider>
        <el-card>
          <div style="margin-bottom: 15px" v-for="item in 10" :key="item">
            <el-link href="##" target="_blank">
              数据库课程设计
            </el-link>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <div style="margin-bottom: 70px;">
      <el-pagination
          background
          :page-sizes="[10, 20, 30, 40]"
          layout="sizes,prev, pager, next"
          :total="1000">
      </el-pagination>
    </div>

  </div>
</template>
<script>
export default {
  data() {
    return {}
  }
}

</script>
