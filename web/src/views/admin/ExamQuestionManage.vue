<template>
  <div>
    <div class="pb10px">
      <el-form ref="searchForm" :model="searchForm" inline>
        <el-form-item label="题目" prop="name">
          <el-input v-model="searchForm.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-button type="primary" @click="handleSearch" icon="el-icon-search">搜 索</el-button>
          <el-button @click="resetForm" icon="el-icon-refresh">重 置</el-button>
        </el-form-item>

      </el-form>
    </div>
    <el-button type="primary" @click="handleAdd" icon="el-icon-circle-plus-outline">新 增</el-button>

    <el-button @click="batchDelete" :disabled="ids.length<=0" icon="el-icon-delete">批量删除</el-button>
    <el-table
        ref="multipleTable"
        :data="listData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID"></el-table-column>

      <el-table-column prop="name" label="题目"></el-table-column>
      <el-table-column prop="type" label="题型"></el-table-column>
      <el-table-column prop="options" label="选择题选项"></el-table-column>
      <el-table-column prop="answer" label="答案"></el-table-column>
      <el-table-column prop="score" label="分值"></el-table-column>
      <el-table-column prop="userId" label="用户ID"></el-table-column>
      <!--      <el-table-column prop="img" label="图片">-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-image-->
      <!--              style="width: 60px;height: 60px"-->
      <!--              :src="scope.row.img"-->
      <!--              :preview-src-list="[scope.row.img]"-->
      <!--          >-->
      <!--          </el-image>-->
      <!--        </template>-->
      <!--      </el-table-column>-->

      <el-table-column label="操作" fixed="right" width="200">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleEdit(scope.$index,scope.row)" icon="el-icon-edit-outline">编 辑
          </el-button>
          <el-button @click="handleDelete(scope.row)" icon="el-icon-delete">删 除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        @current-change="currentChange"
        @size-change="sizeChange"
        :page-size="pageInfo.pageSize"
        :current-page="pageInfo.pageNum"
        background
        layout="prev, sizes,pager, next"
        :page-sizes="[10, 20, 30, 40, 50, 100]"
        :total="total">
    </el-pagination>

    <el-drawer
        :title="formData.id?'编辑':'新增'"
        :visible.sync="isDrawerDialog"
        size="400px"
        direction="rtl"
        :wrapper-closable="false"
    >
      <div class="demo-drawer__content" style="padding-right:40px;box-sizing: border-box">
        <el-form :model="formData" label-width="100px">
          <el-form-item label="题目">
            <el-input v-model="formData.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="题型">
            <el-select v-model="formData.type" clearable placeholder="请选择题型" style="width: 100%" :disabled="formData.id!==undefined">
              <el-option v-for="item in questionType" :key="item" :lable="item" :value="item"  autocomplete="off"></el-option>
            </el-select>

          </el-form-item>
          <el-form-item label="选择题选项" v-if="formData.type==='单选题'||formData.type==='多选题'">

            <el-input v-model="selectList[i]" v-for="(item,i) in selectList" :key="item" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="答案">
            <el-input v-model="formData.answer" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="分值">
            <el-input v-model="formData.score" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer" style="display: flex;justify-content: space-around">
          <el-button @click="isDrawerDialog=false" icon="el-icon-close">取 消</el-button>
          <el-button type="primary" @click="submit" icon="el-icon-upload">提 交</el-button>
        </div>
      </div>
    </el-drawer>

  </div>
</template>

<script>
export default {
  name: "BannerManage",
  data() {
    return {
      searchForm: {},
      listData: [{
        name: "123"
      }],
      formData: {},
      isDrawerDialog: false,
      pageInfo: {
        //当前页
        pageNum: 1,
        //分页大小
        pageSize: 10
      },
      total: 0,
      ids: [],
      questionType: [
        "单选题",
        "多选题",
        "判断题",
        "填空题",
        "主观题"
      ],
      selectList: [
        "A: ",
        "B: ",
        "C: ",
        "D: ",
      ],
      defaultList: [
        "A: ",
        "B: ",
        "C: ",
        "D: ",
      ]
    }
  },
  created() {
    this.getPageList()
  },
  methods: {
    //获取分页数据 定义方法
    getPageList() {
      //分页条件 查询条件
      let data = Object.assign(this.searchForm, this.pageInfo)
      this.$axios.get("/examQuestion/page", {
        params: data
      }).then(res => {
        //获取列表数据
        this.listData = res.data.list
        //获取分页数据
        this.total = res.data.total
      })
    },
    //点击切换页面
    currentChange(e) {
      this.pageInfo.pageNum = e
      this.getPageList()
    },
    sizeChange(e) {
      this.pageInfo.pageSize = e
      this.getPageList()
    },
    handleAdd() {
      this.formData = {}

      this.isDrawerDialog = true
    },
    submit() {
      //新增
      if (!this.formData.id) {
        if(this.formData.type==='单选题'||this.formData.type==='多选题'){
          this.formData.options=JSON.stringify(this.selectList)
        }
        //重置单选多选
        this.selectList=this.defaultList
        this.$axios.post("/examQuestion/add", this.formData).then(res => {
          if (res.code === 200) {
            this.isDrawerDialog = false
            this.$message({
              message: res.msg,
              type: "success"
            })
            this.getPageList()
          }
        })
      } else {
        //更新
        this.$axios.put("/examQuestion/update", this.formData).then(res => {
          if (res.code === 200) {
            this.isDrawerDialog = false
            this.$message({
              message: res.msg,
              type: "success"
            })
            this.getPageList()
          }
        })
        //重置单选多选
        this.selectList=this.defaultList
      }
      this.selectList=this.defaultList
    },
    handleEdit(index, row) {
      this.formData = Object.assign({}, row)
      if(this.formData.type==='单选题'||this.formData.type==='多选题'){
        this.selectList=JSON.parse(this.formData.options)
      }
      this.isDrawerDialog = true
    },

    // uploadImgSuccess(res) {
    //   this.$set(this.formData, "img", res.data.url)
    // },
    //单选和多选删除 获取id
    batchDelete(rows) {

      console.log(rows)
      console.log("==================")
      console.log(typeof rows)
      console.log(rows.length)
      //区别：多选单击的时候undefined,单选的时候显示为1

      if (rows.length === undefined) {
        //获取表格中已经被选择的元素
        rows = this.$refs.multipleTable.selection
      }

      //获取id
      this.ids = rows.map(item => item.id)
      //发送请求
      //es6中的模板字符串
      this.$confirm(`此操作将永久删除ID为[${this.ids}]的数据, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.delete("/examQuestion/delBatch", {data: this.ids}).then(res => {
          if (res.code === 200) {
            this.$message({
              type: 'success',
              message: res.msg
            });
            //重置ids变量
            this.ids = []
            //重新发起请求
            this.getPageList()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });

        //取消选中
        this.$refs.multipleTable.clearSelection()
      });

    },
    handleDelete(row) {
      this.batchDelete([row])
    },
    handleSelectionChange(rows) {
      //获取id
      this.ids = rows.map(item => item.id)
    },
    handleSearch() {
      //当我们点击搜索的时候，页面会切换到第一页
      this.pageInfo.pageNum = 1
      this.getPageList()
    },
    // 重置表单
    resetForm() {
      // 调用searchForm表单的resetFields方法，重置表单
      this.$refs['searchForm'].resetFields()
    }


  }
}
</script>

<style scoped>

</style>