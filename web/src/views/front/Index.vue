<template>
  <div>
    <!--el-col将一行分为24份-->
    <el-row>
      <!--      {{bannerList}}-->
      <!--      id bigint AI PK-->
      <!--      name varchar(255)-->
      <!--      img varchar(255)-->
      <!--      link varchar(255)-->
      <el-col :span="16">
        <div style="width: 95%">
          <el-carousel style="height: 350px" :interval="10000" class="swiper">
            <el-carousel-item v-for="item in bannerList" :key="item.id">
              <a :href="item.link">
                <img style="width: 100%;height:100%" :src='item.img' alt="">
              </a>
            </el-carousel-item>
          </el-carousel>
        </div>

      </el-col>
      <el-col :span="8">
        <el-divider>系统通知</el-divider>
        <el-collapse style="height: 300px;overflow-y: auto">
          <el-collapse-item title="系统通知" v-for="item in 10" :key="item">
            <div>发布时间：2024-10-10</div>
            <div>发布内容：内容</div>
          </el-collapse-item>
        </el-collapse>


      </el-col>
    </el-row>
    <!--        Row 组件 提供 gutter 属性来指定每一栏之间的间隔，默认间隔为 0。-->
    <el-row :gutter="10" style="    margin-bottom: 60px;">
      <el-divider>在线考试系统</el-divider>
      <el-col :span="19">
        <div>
          <el-divider content-position="left">最新资讯</el-divider>
          <el-card v-for="item in 5" :key="item"
                   style="margin-bottom: 10px;cursor: pointer">
            <div style="display: flex" @click="$router.push('/front/detail?id=' + item)">
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
          <div style="margin-bottom: 15px" v-for="item in linkList" :key="item">
            <el-link :href="item.link" target="_blank">
              {{item.name}}
            </el-link>
          </div>
        </el-card>

      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      bannerList: [],
      linkList: []
    }
  },
  created() {
    this.$axios.get('/banner/list').then(res => {
      this.bannerList = res.data
    })
    //获取全部的友链
    this.$axios.get('/link/list').then(res => {
      this.linkList = res.data
    })
  },
  methods: {}
}
</script>