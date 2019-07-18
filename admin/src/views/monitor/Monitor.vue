<template>
    <div>
      <el-col :span="4">
        <el-input v-model="searchState" placeHolder="按操作搜索"></el-input>
      </el-col>
      <el-col :span="4">
        <div class="block">
          <el-date-picker
            v-model="dateValue"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </div>
      </el-col>
      <el-col :span="4" :offset="12">
        <export-excel v-if="dataInPage() != null" :list="dataInPage()"
                      :t-header="['#ID', '创建时间', 'IP地址', '服务器端方法', '参数', '操作描述', '响应时间']"
                      :t-value="['ID', 'CREATE_TIME', 'IP', 'METHOD', 'PARAMS', 'OPERATION', '_TIME']"></export-excel>
      </el-col>

      <el-table :data="dataInPage().slice((currentPage-1)*cntPerPage,currentPage*cntPerPage)" style="width: 100%">
        <el-table-column
          sortable
          fixed="left"
          prop="ID"
          label="#ID"
          width="180">
        </el-table-column>
        <el-table-column
          sortable
          prop="CREATE_TIME"
          label="创建时间"
          width="230">
        </el-table-column>
        <el-table-column
          prop="IP"
          label="IP地址"
          width="180">
        </el-table-column>
        <el-table-column
          prop="METHOD"
          label="服务器端方法"
          width="230">
        </el-table-column>
        <el-table-column
          prop="PARAMS"
          label="参数"
          width="180">
        </el-table-column>
        <el-table-column
          prop="OPERATION"
          label="操作描述"
          width="180">
        </el-table-column>
        <el-table-column
          sortable
          prop="_TIME"
          label="响应时间"
          width="180">
        </el-table-column>
      </el-table>

      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 40]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="dataInPage().length">
        </el-pagination>
      </div>
    </div>
</template>

<script>
  import ExportExcel from "../../components/ExportExcel"
  export default {
    name: "Monitor",
    components: {ExportExcel},
    data() {
      return {
        logList: [
          {
            CREATE_TIME: "2019-07-05T02:36:35.000+0000",
            ID: 1,
            IP: "127.0.0.1",
            METHOD: "com.liznsalt.javatask.takeout.common.controller.Test.methodOne()",
            OPERATION: "执行方法一",
            PARAMS: "  name: Zhang",
            USERNAME: "Zhang San",
            _TIME: 0
          }
        ],

        currentPage: 1,
        cntPerPage: 5,

        searchState: '',

        dateValue: ''
      }
    },
    created() {
      this.$get('/log/all').then(res => {
        this.logList = res.data
      }).catch(err => console.log(err))
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`);
        this.cntPerPage = val
      },
      handleCurrentChange(val) {
        // console.log(`当前页: ${val}`);
        this.currentPage = val
      },
      dataInPage() {
        let data = this.logList
          .filter(data =>
          !this.searchState || data.OPERATION.toLowerCase().includes(this.searchState.toLowerCase()))
          .filter(data =>
            !this.dateValue || (new Date(this.dateValue[0]) <= new Date(data.CREATE_TIME) && new Date(data.CREATE_TIME) <=  new Date(this.dateValue[1])))
        this.currentPage = Math.min(this.currentPage, Math.ceil(data.length / this.cntPerPage))
        return data
      }
    }
  }
</script>

<style scoped>

</style>
