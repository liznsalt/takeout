<template>
  <div>
    <el-col :span="4">
      <el-input v-model="searchConsumer" placeholder="按用户名字搜索"></el-input>
    </el-col>
    <el-col :span="4">
      <el-input v-model="searchShop" placeholder="按商家名字搜索"></el-input>
    </el-col>
    <el-col :span="4">
      <el-input v-model="searchGoods" placeholder="按商品名字搜索"></el-input>
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
    <el-col :span="4" :offset="4">
      <export-excel v-if="dataInPage() != null" :list="dataInPage()"
                    :t-header="['#ID', '交易时间', '购买者', '商家', '商品名', '购买数量', '总价', '状态']"
                    :t-value="['id', 'buyTime', 'consumerName', 'shopName', 'goodsName', 'num', 'total', 'state']"></export-excel>
    </el-col>

    <el-table :data="dataInPage().slice((currentPage-1)*cntPerPage,currentPage*cntPerPage)" style="width: 100%">
      <el-table-column
        sortable
        fixed="left"
        prop="id"
        label="#ID"
        width="180">
      </el-table-column>
      <el-table-column
        sortable
        prop="buyTime"
        label="交易时间"
        width="230">
      </el-table-column>
      <el-table-column
        prop="consumerName"
        label="购买者"
        width="180">
      </el-table-column>
      <el-table-column
        prop="shopName"
        label="商家"
        width="180">
      </el-table-column>
      <el-table-column
        prop="goodsName"
        label="商品名"
        width="180">
      </el-table-column>
      <el-table-column
        sortable
        prop="num"
        label="购买数量"
        width="180">
      </el-table-column>
      <el-table-column
        sortable
        prop="total"
        label="总价"
        width="180">
      </el-table-column>
      <el-table-column
        fixed="right"
        prop="state"
        label="状态"
        :filters="[{ text: '已完成', value: '已完成' },{ text: '已取消', value: '已取消' }, { text: '未完成', value: '未完成' }, { text: '待评价', value: '待评价' }]"
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag
            :type="tagType(scope.row)"
            disable-transitions>{{scope.row.state}}</el-tag>
        </template>
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
    name: "OrderTable",
    components: {ExportExcel},
    data() {
      return {
        orderList: [
          {
            buyTime: "2019-06-27T18:03:21.000+0000",
            cid: 1,
            consumerName: "Zhang San",
            gid: 3,
            goodsName: "炒面",
            id: 4,
            num: 12,
            picture: "http://localhost:8080/takeout/upload/201907070859253.jpg",
            price: 200,
            shopName: "mai",
            sid: 1,
            state: "已完成",
            total: 100
          }
        ],

        currentPage: 1,
        cntPerPage: 5,

        searchConsumer: '',
        searchShop: '',
        searchGoods: '',

        dateValue: ''
      }
    },
    created() {
      this.$get('/admin/getAllOrderHistory').then(res => {
        this.orderList = res.data
      }).catch(err => console.log(err))
    },
    methods: {
      filterTag(value, row) {
        return row.state === value;
      },
      tagType(row) {
        switch (row.state) {
          case '已完成' : return 'primary'
          case '未完成' : return 'danger'
          case '待评价' : return 'warning'
          case '已取消' : return 'info'
        }
      },

      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`);
        this.cntPerPage = val
      },
      handleCurrentChange(val) {
        // console.log(`当前页: ${val}`);
        this.currentPage = val
      },

      dataInPage() {
        let data = this.orderList
          .filter(data =>
            !this.searchConsumer || data.consumerName.toLowerCase().includes(this.searchConsumer.toLowerCase()))
          .filter(data =>
            !this.searchShop || data.shopName.toLowerCase().includes(this.searchShop.toLowerCase()))
          .filter(data =>
            !this.searchGoods || data.goodsName.toLowerCase().includes(this.searchGoods.toLowerCase()))
          .filter(data =>
            !this.dateValue || (new Date(this.dateValue[0]) <= new Date(data.buyTime) && new Date(data.buyTime) <=  new Date(this.dateValue[1])))

        this.currentPage = Math.min(this.currentPage, Math.ceil(data.length / this.cntPerPage))
        return data
      }
    }
  }
</script>

<style scoped>

</style>
