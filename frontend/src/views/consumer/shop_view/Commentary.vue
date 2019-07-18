<template>
  <comment :comments="comments" @add="addNewReplay" @remove="removeReply"></comment>
</template>

<script>
  import * as CommentData from '@/utils/mockdata'
  import comment from '@/components/comment'

  export default {
    name: "Commentary",
    components: {
      comment
    },
    props: ['sid'],
    data() {
      return {
        SID: '',

        comments: [
          {
            id: 'comment0001', //主键id
            date: '2018-07-05 08:30',  //评论时间
            ownerId: 'talents100020', //文章的id
            fromId: 'errhefe232213',  //评论者id
            fromName: '犀利的评论家',   //评论者昵称
            fromAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg', //评论者头像
            likeNum: 3, //点赞人数
            content: '非常靠谱的程序员',  //评论内容
            stars: '',
            show: false,
            reply: [  //回复，或子评论
              {
                id: '34523244545',  //主键id
                commentId: 'comment0001',  //父评论id，即父亲的id
                fromId: 'observer223432',  //评论者id
                fromName: '夕阳红',  //评论者昵称
                fromAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', //评论者头像
                toId: 'errhefe232213',  //被评论者id
                toName: '犀利的评论家',  //被评论者昵称
                toAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg',  //被评论者头像
                content: '赞同，很靠谱，水平很高',  //评论内容
                date: '2018-07-05 08:35',   //评论时间
                replyRole: ''
              }
            ]
          }
        ]
      }
    },
    created() {
      // console.log(this.$route.params)
      this.SID = this.sid
      this.refreshPage()
    },
    watch: {
      'sid' () {
        // console.log(this.$route.params)
        this.SID = this.sid
        this.refreshPage()
      }
    },
    methods: {
      refreshPage() {
        // this.commentData = CommentData.comment.data;
        this.comments = []

        // 1 先获取评论
        this.$get('/commentary/getInfoBySid', {
          Sid: this.SID
        }).then(res => {
          let allComments = res.data.data
          for (let i = 0, len = allComments.length; i < len; i++) {
            this.comments.push({
              id: allComments[i].id,
              date: allComments[i].time,
              ownerId: allComments[i].id,
              fromId: allComments[i].cid,
              fromName: allComments[i].cname,
              fromAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg',
              likeNum: 3,
              content: allComments[i].content,
              reply: [], // 先为空
              stars: allComments[i].stars, // 评价
              show: false // 是否展开显示
            })
            // 2 再获取回复
            let nowCoId = allComments[i].id // 当前评论的id
            // 2.1 商家回复
            this.$get('/shopReply/getInfoByCoid', {
              coid: nowCoId
            }).then(res => {
              let allShopReply = res.data.data
              for (let j = 0, len1 = allShopReply.length; j < len1; j++) {
                this.comments[i].reply.push({
                  id: String(allShopReply[j].coid) + String(allShopReply[j].sid),  //主键id
                  commentId: allComments[i].id,  //父评论id，即父亲的id
                  fromId: allShopReply[j].sid,  //评论者id
                  fromName: allShopReply[j].sname + "(商家)",  //评论者昵称
                  fromAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', //评论者头像
                  toId: allComments[i].cid,  //被评论者id
                  toName: allComments[i].cname,  //被评论者昵称
                  toAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg',  //被评论者头像
                  content: allShopReply[j].content,  //评论内容
                  date: allShopReply[j].time ,  //评论时间
                  replyRole: 'shop'
                })
              }
              // 2.2 用户回复
              this.$get('/consumerReply/getInfoByCoid', {
                coid: nowCoId
              }).then(res => {
                let allConsumerReply = res.data.data
                for (let j = 0, len1 = allConsumerReply.length; j < len1; j++) {
                  this.comments[i].reply.push({
                    id: String(allConsumerReply[j].coid) + String(allConsumerReply[j].cid),  //主键id
                    commentId: allComments[i].id,  //父评论id，即父亲的id
                    fromId: allConsumerReply[j].cid,  //评论者id
                    fromName: allConsumerReply[j].cname,  //评论者昵称
                    fromAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', //评论者头像
                    toId: allComments[i].cid,  //被评论者id
                    toName: allComments[i].cname,  //被评论者昵称
                    toAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg',  //被评论者头像
                    content: allConsumerReply[j].content,  //评论内容
                    date: allConsumerReply[j].time,   //评论时间
                    replyRole: 'consumer'
                  })
                }
              }).catch(err => console.log(err))
            }).catch(err => console.log(err))
          }
        }).catch(err => console.log(err))

      }, // refresh end
      /**
       * idx下标处添加新回复
       * @param idx
       * @param comment
       */
      addNewReplay(idx, comment) {
        this.comments[idx].reply.splice(0, 0, comment)
      },
      removeReply(idx, ridx) {
        this.comments[idx].reply.splice(ridx, 1)
      }
    }
  }
</script>

<style scoped>

</style>
