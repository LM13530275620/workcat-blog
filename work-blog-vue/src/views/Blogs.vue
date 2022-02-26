<template>
  <div class="mcontaner">
    <Header></Header>

    <div class="block">
      <el-timeline>

        <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in blogs">
          <el-card>
            <h4>
              <router-link :to="{name: 'BlogDetail', params: {blogId: blog.id}}"><!--详情页跳转  params:{blogId:blog.id}}并携带每个文章的id-->
                {{blog.title}}
              </router-link>
            </h4>
            <p>{{blog.description}}</p>
          </el-card>
        </el-timeline-item>

      </el-timeline>

      <el-pagination class="mpage"
                     background
                     layout="prev, pager, next"
                     :current-page="currentPage"
                     :page-size="pageSize"
                     :total="total"
                     @current-change=page>
      </el-pagination>

    </div>

  </div>
</template>

<script>
  import Header from "../components/Header";//导入页面(公共插件)

  export default {
    name: "Blogs.vue",
    components: {Header},//导入页面
    data() {
      return {
        blogs: {},
        currentPage: 1,//默认第一页
        total: 0,//总数据量
        pageSize: 5//每个分页文章数量
      }
    },
    methods: {
      //页面数据请求
      page(currentPage) {
        const _this = this
        _this.$axios.get("/blogs?currentPage=" + currentPage).then(res => {
          console.log(res)
          _this.blogs = res.data.data.records//赋值数据
          _this.currentPage = res.data.data.current
          _this.total = res.data.data.total
          _this.pageSize = res.data.data.size

        })
      }
    },
    created() {//页面加载时调用页面数据请求方法
      this.page(1)//(参数默认第一页)
    }
  }
</script>

<style scoped>

  .mpage {
    margin: 0 auto;
    text-align: center;
  }

</style>