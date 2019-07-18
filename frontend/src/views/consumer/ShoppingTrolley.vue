<template>
  <div>
    <el-col :span="14" :offset="5">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick" >
        <el-tab-pane label="全部" name="first">

          <el-col :offset="1">
            <el-table :data="dataInPage">
              <el-table-column type="expand" border stripe highlight-current-row>
                <template slot-scope="props">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-card class="box-card" v-for="card in props.row">
                      <el-form-item slot="header" label="订单编号">
                        <span>{{ card.id }}</span>
                        <el-button v-if="card.state==='未完成'" type="warning" style="margin-left:500px" size="small" @click="cancelOrder(card)">取消订单</el-button>
                      </el-form-item>
                      <el-container>
                        <el-aside>
                          <!-- 图片 -->
                          <el-form-item>
                            <div>
                              <!-- 默认 -->
                              <el-image v-if="card.picture === '' || card.picture === null" style="width: 200px; height: 200px" :src="require('@/assets/img/default.jpg')" fit="fill"></el-image>
                              <!-- 商家添加的 -->
                              <el-image v-else style="width: 200px; height: 200px" :src="card.picture" fit="fill"></el-image>
                            </div>
                          </el-form-item>
                        </el-aside>
                        <el-main>
                          <div><el-form-item label="商品编号">{{ card.gid }}</el-form-item></div>
                          <div><el-form-item label="商品名称">{{ card.goodsName}}</el-form-item></div>
                          <div class="price"><el-form-item label="商品单价">￥{{ card.price }}</el-form-item></div>
                          <div><el-form-item label="购买数量">{{ card.num }}</el-form-item></div>
                          <div><el-form-item label="状态">{{ card.state }}</el-form-item></div>
                        </el-main>
                      </el-container>
                    </el-card>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column prop="date" label="订单时间" sortable width="180" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.time }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="shopname" label="商家姓名" width="150" align="center">
                <template slot-scope="scope">
                  <el-link @click.native="$router.push(`/shop/${scope.row.sid}`)">{{scope.row.shopName}}</el-link>
                </template>
              </el-table-column>
              <el-table-column prop="price" label="订单总价" sortable width="150" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.total }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                  <el-button v-if="!scope.row.some(x => (x.state === '已完成' || x.state === '未完成')) && scope.row.some(x => x.state === '待评价')" type="success" size="small" @click="readyToComment(scope.row)">评价</el-button>
                  <el-button v-if="scope.row.some(x => x.state === '未完成')" type="warning" size="small" @click="cancelSomeOrder(scope.row)">取消</el-button>
                  <el-button v-if="!scope.row.some(x => x.state !== '已完成')" type="info" size="small" disabled>已完成</el-button>
                  <el-button v-if="!scope.row.some(x => x.state !== '已取消')" type="info" size="small" disabled>已取消</el-button>
                </template>
              </el-table-column>
              <!--   联系       -->
              <el-table-column label="联系" width="150">
                <template slot-scope="scope">
                  <el-button type="success" size="small" @click="contactTo(scope.row)" round icon="el-icon-chat-dot-round"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
<!------------------------------------------------------------------------------------------------------------------------->
        <el-tab-pane label="已完成" name="second">
          <el-col :offset="1">
            <el-table :data="dataInPage.filter(x => !x.some(y => y.state !== '已完成'))">
              <el-table-column type="expand" border stripe highlight-current-row>
                <template slot-scope="props">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-card class="box-card" v-for="card in props.row">
                      <el-form-item slot="header" label="订单编号">
                        <span>{{ card.id }}</span>
                      </el-form-item>
                      <el-container>
                        <el-aside>
                          <!-- 图片 -->
                          <el-form-item>
                            <div>
                              <!-- 默认 -->
                              <el-image v-if="card.picture === '' || card.picture === null" style="width: 200px; height: 200px" :src="require('@/assets/img/default.jpg')" fit="fill"></el-image>
                              <!-- 商家添加的 -->
                              <el-image v-else style="width: 200px; height: 200px" :src="card.picture" fit="fill"></el-image>
                            </div>
                          </el-form-item>
                        </el-aside>
                        <el-main>
                          <div><el-form-item label="商品编号">{{ card.gid }}</el-form-item></div>
                          <div><el-form-item label="商品名称">{{ card.goodsName}}</el-form-item></div>
                          <div class="price"><el-form-item label="商品单价">￥{{ card.price }}</el-form-item></div>
                          <div><el-form-item label="购买数量">{{ card.num }}</el-form-item></div>
                        </el-main>
                      </el-container>
                    </el-card>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column prop="date" label="订单时间" sortable width="180" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.time }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="shopname" label="商家姓名" width="150" align="center">
                <template slot-scope="scope">
                  <el-link @click.native="$router.push(`/shop/${scope.row.sid}`)">{{scope.row.shopName}}</el-link>
                </template>
              </el-table-column>
              <el-table-column prop="price" label="订单总价" sortable width="150" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.total }}</span>
                </template>
              </el-table-column>
              <!--   联系       -->
              <el-table-column label="联系" width="150">
                <template slot-scope="scope">
                  <el-button type="success" size="small" @click="contactTo(scope.row)" round icon="el-icon-chat-dot-round"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
<!--=====================================================================================================================-->
        <el-tab-pane label="未完成" name="third">
          <el-col :offset="1">
            <el-table :data="dataInPage.filter(x => x.some(y => y.state === '未完成'))">
              <el-table-column type="expand" border stripe highlight-current-row>
                <template slot-scope="props">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-card class="box-card" v-for="card in props.row" v-if="card.state==='未完成'">
                      <el-form-item slot="header" label="订单编号">
                        <span>{{ card.id }}</span>
                        <el-button type="warning" style="margin-left:500px" size="small" @click="cancelOrder(card)">取消订单</el-button>
                      </el-form-item>
                      <el-container>
                        <el-aside>
                          <!-- 图片 -->
                          <el-form-item>
                            <div>
                              <!-- 默认 -->
                              <el-image v-if="card.picture === '' || card.picture === null" style="width: 200px; height: 200px" :src="require('@/assets/img/default.jpg')" fit="fill"></el-image>
                              <!-- 商家添加的 -->
                              <el-image v-else style="width: 200px; height: 200px" :src="card.picture" fit="fill"></el-image>
                            </div>
                          </el-form-item>
                        </el-aside>
                        <el-main>
                          <div><el-form-item label="商品编号">{{ card.gid }}</el-form-item></div>
                          <div><el-form-item label="商品名称">{{ card.goodsName}}</el-form-item></div>
                          <div class="price"><el-form-item label="商品单价">￥{{ card.price }}</el-form-item></div>
                          <div><el-form-item label="购买数量">{{ card.num }}</el-form-item></div>
                        </el-main>
                      </el-container>
                    </el-card>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column prop="date" label="订单时间" sortable width="180" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.time }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="shopname" label="商家姓名" width="150" align="center">
                <template slot-scope="scope">
                  <el-link @click.native="$router.push(`/shop/${scope.row.sid}`)">{{scope.row.shopName}}</el-link>
                </template>
              </el-table-column>
              <el-table-column prop="price" label="订单总价" sortable width="150" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ notFinishPrice(scope.row) }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                  <el-button type="warning" size="small" @click="cancelSomeOrder(scope.row)">取消</el-button>
                </template>
              </el-table-column>
              <!--   联系       -->
              <el-table-column label="联系" width="150">
                <template slot-scope="scope">
                  <el-button type="success" size="small" @click="contactTo(scope.row)" round icon="el-icon-chat-dot-round"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
<!----------------------------------------------------------------------------------------------------------------------->
        <el-tab-pane label="已取消" name="fourth">
          <el-col :offset="1">
            <el-table :data="dataInPage.filter(x => !x.some(y => y.state !== '已取消'))">
              <el-table-column type="expand" border stripe highlight-current-row>
                <template slot-scope="props">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-card class="box-card" v-for="card in props.row">
                      <el-form-item slot="header" label="订单编号">
                        <span>{{ card.id }}</span>
                      </el-form-item>
                      <el-container>
                        <el-aside>
                          <!-- 图片 -->
                          <el-form-item>
                            <div>
                              <!-- 默认 -->
                              <el-image v-if="card.picture === '' || card.picture === null" style="width: 200px; height: 200px" :src="require('@/assets/img/default.jpg')" fit="fill"></el-image>
                              <!-- 商家添加的 -->
                              <el-image v-else style="width: 200px; height: 200px" :src="card.picture" fit="fill"></el-image>
                            </div>
                          </el-form-item>
                        </el-aside>
                        <el-main>
                          <div><el-form-item label="商品编号">{{ card.gid }}</el-form-item></div>
                          <div><el-form-item label="商品名称">{{ card.goodsName}}</el-form-item></div>
                          <div class="price"><el-form-item label="商品单价">￥{{ card.price }}</el-form-item></div>
                          <div><el-form-item label="购买数量">{{ card.num }}</el-form-item></div>
                        </el-main>
                      </el-container>
                    </el-card>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column prop="date" label="订单时间" sortable width="180" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.time }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="shopname" label="商家姓名" width="150" align="center">
                <template slot-scope="scope">
                  <el-link @click.native="$router.push(`/shop/${scope.row.sid}`)">{{scope.row.shopName}}</el-link>
                </template>
              </el-table-column>
              <el-table-column prop="price" label="订单总价" sortable width="150" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.total }}</span>
                </template>
              </el-table-column>
              <!--   联系       -->
              <el-table-column label="联系" width="150">
                <template slot-scope="scope">
                  <el-button type="success" size="small" @click="contactTo(scope.row)" round icon="el-icon-chat-dot-round"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
<!--===================================================================================================================-->
        <el-tab-pane label="待评价" name="fifth">
          <el-col :offset="1">
            <el-table :data="dataInPage.filter(y => (!y.some(x => (x.state === '已完成' || x.state === '未完成')) && y.some(x => x.state === '待评价')))">
              <el-table-column type="expand" border stripe highlight-current-row>
                <template slot-scope="props">
                  <el-form label-position="left" inline class="demo-table-expand">
                    <el-card class="box-card" v-for="card in props.row">
                      <el-form-item slot="header" label="订单编号">
                        <span>{{ card.id }}</span>
                      </el-form-item>
                      <el-container>
                        <el-aside>
                          <!-- 图片 -->
                          <el-form-item>
                            <div>
                              <!-- 默认 -->
                              <el-image v-if="card.picture === '' || card.picture === null" style="width: 200px; height: 200px" :src="require('@/assets/img/default.jpg')" fit="fill"></el-image>
                              <!-- 商家添加的 -->
                              <el-image v-else style="width: 200px; height: 200px" :src="card.picture" fit="fill"></el-image>
                            </div>
                          </el-form-item>
                        </el-aside>
                        <el-main>
                          <div><el-form-item label="商品编号">{{ card.gid }}</el-form-item></div>
                          <div><el-form-item label="商品名称">{{ card.goodsName}}</el-form-item></div>
                          <div class="price"><el-form-item label="商品单价">￥{{ card.price }}</el-form-item></div>
                          <div><el-form-item label="购买数量">{{ card.num }}</el-form-item></div>
                          <div><el-form-item label="状态">{{ card.state }}</el-form-item></div>
                        </el-main>
                      </el-container>
                    </el-card>
                  </el-form>
                </template>
              </el-table-column>
              <el-table-column prop="date" label="订单时间" sortable width="180" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.time }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="shopname" label="商家姓名" width="150" align="center">
                <template slot-scope="scope">
                  <el-link @click.native="$router.push(`/shop/${scope.row.sid}`)">{{scope.row.shopName}}</el-link>
                </template>
              </el-table-column>
              <el-table-column prop="price" label="订单总价" sortable width="150" align="center">
                <template slot-scope="scope">
                  <span style="margin-left: 10px">{{ scope.row.total }}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                  <el-button type="success" size="small" @click="readyToComment(scope.row)">评价</el-button>
                </template>
              </el-table-column>
              <!--   联系       -->
              <el-table-column label="联系" width="150">
                <template slot-scope="scope">
                  <el-button type="success" size="small" @click="contactTo(scope.row)" round icon="el-icon-chat-dot-round"></el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-tab-pane>
      </el-tabs>

    </el-col>
<!-- ----------------------------------------------------------------------------------------------------->
    <el-dialog title="评价" :visible.sync="dialogFormVisible" width="50%" height="80%">
      <el-form :model="commentForm">
        <el-form-item label="宝贝评价" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="commentForm.content" placeholder="请输入评价内容" ref="input"
                    :autosize="{ minRows: 6}" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-rate v-model="commentForm.stars" score-template="{title}" show-text></el-rate>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitComment(nowSubmit)">确 定</el-button>
      </div>
    </el-dialog>

<!----------------------------------------------------------------------------------------------------------------------->
    <el-dialog
      title="聊天"
      :visible.sync="dialogContactVisible"
      width="65%">
      <ContactDialog :other="nowContactShop" v-if="dialogContactVisible"></ContactDialog>
    </el-dialog>
  </div>
</template>


<script>
  import util from "@/utils/util"
  import ContactDialog from "@/components/ContactDialog"

  export default {
    name: "ShoppingTrolley",
    components: {ContactDialog},
    data() {
      return {
        title: '店铺服务星级评价',
        radio: '全部',
        id:'',
        activeName: 'first',
        value:'',

        orderList: [
          {
            cid: 1,
            consumerName: "Zhang San",
            gid: 22,
            goodsName: "烤鲈鱼",
            id: 18,
            num: 3,
            picture: "http://localhost:8080/takeout/upload/201907022120098.jpg",
            price: 120,
            shopName: "mai",
            sid: 1,
            state: "未完成",
            time: "2019-07-07 16:45:16",
            total: 120
          }
        ],
        dialogTableVisible: false,
        dialogFormVisible: false,
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        formLabelWidth: '120px',
        fromLabelHeight: '80px',

        commentForm: {
          content: '',
          stars: 0
        },
        nowSubmit: '',

        dialogContactVisible: false,
        nowContactShop: '',

        // FIXME: NEW 展示数据
        formatData: [],
        // 分页

      }
    },
    created() {
      this.$get('/consumer/getOrderHistory', {
        CID: this.$db.get('USER_ID')
      }).then(res => {
        console.log(res.data);
        this.orderList = res.data.data
        //
        this.formatData = util.filterByTimeAndName(this.orderList, 'time', 'shopName')
      }).catch(err => console.log(err))
    },

    methods: {
      filterTag(value, row) {
        return row.state === value;
      },
      tagType(row) {
        switch (row.state) {
          case '已完成' :  return 'primary';
          case '未完成' :  return 'danger';
          case '待评价' :  return 'warning';
          case '已取消' :  return 'info'
        }
      },

      handleClick(tab, event) {
        console.log(tab, event);
      },

      cancelOrder(row){
        // console.log(row)
        this.$confirm('是否确认取消此订单?',  {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$get('/consumer/cancelOrder', {
            OID:row.id
          }).then(() => {
            // console.log(res.data);
            for (let i = 0 ; i < this.orderList.length; i++){
              if (row.id === this.orderList[i].id){
                this.$set(this.orderList[i], 'state', '已取消')
                this.$message({type: 'success', message: '已成功取消该订单'})
              }
            }
          })
        }).catch(err => {
          console.log(err);
          this.$message({type: 'info', message: '取消操作'})
        })
      },
      cancelSomeOrder(row) {
        this.$confirm('注意:此操作只会取消此订单内未完成的商品,是否继续?',  {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          for (let order of row) {
            if (order.state !== '未完成') return
            await this.$get('/consumer/cancelOrder', {OID: order.id}).then(res => {
              for (let i = 0 ; i < this.orderList.length; i++) {
                if (order.id === this.orderList[i].id) {
                  this.$set(this.orderList[i], 'state', '已取消')
                }
              }
            })
          }
          this.$message({type: 'success', message: '已成功取消该订单'})
        }).catch(() => {
          this.$message({type: 'info', message: '取消操作'})
        })
      },//

      submitComment(row) {
        let date = new Date().Format("yyyy-MM-dd hh:mm:ss");
        console.log(this.commentForm.content);
        console.log(this.commentForm.stars);
        this.$get('/commentary/addCommentary',{
          cid: this.$db.get('USER_ID'),
          sid: row.sid,
          cotime: date,
          content: this.commentForm.content,
          stars: this.commentForm.stars
        }).then(async (res) => {
          // console.log(res.data.content);
          this.commentForm.content = '';
          this.commentForm.stars = 0;
          this.dialogFormVisible = false;
          this.$message({
            type: 'info',
            message: '评价成功！'
          })
          for (let order of row) {
            // FIXME: 完成所有订单
            if (order.state !== '待评价') return
            await this.$get('/ShoppingCart/finishOrder', {OID: order.id})
            for (let i = 0; i < this.orderList.length; i++) {
              if (order.id === this.orderList[i].id) {
                this.$set(this.orderList[i], 'state', '已完成');
              }
            }
          }
        })
      },

      readyToComment(row) {
        this.nowSubmit = row
        this.dialogFormVisible = true
      },

      contactTo(row) {
        this.nowContactShop = row.sid
        this.dialogContactVisible = true
      },

      notFinishPrice(row) {
        let data = row.filter(x=>x.state==='未完成')
        let res = 0
        for (let order of data) {
          res += order.total
        }
        return res
      }
    },

    computed: {
      dataInPage() {
        return this.formatData
      }
    }

  }
</script>

<style scoped>
  .image{
    width:250px;
    height:200px;
  }
  .text{
    margin-top:50px;
    font-weight: bold;
  }
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }

  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 200px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-left: 0;
    margin-bottom: 0;
  }
  .price{
    color:#FFCD56;
    font-size:30px;
  }
  .box-card{
    margin-top:15px;
    margin-bottom:15px;
  }
</style>

