<template>
  <el-button :loading="downloadLoading" type="primary" @click="handleDownload">导出 Excel</el-button>
</template>

<script>
  export default {
    name: 'ExportExcel',
    props: {
      list: {
        required: true,
        type: Array
      },
      tHeader: {
        required: true,
        type: Array
      },
      tValue: {
        required: true,
        type: Array
      },
      filename: {
        type: String,
        default: '导出数据'
      }
    },
    data() {
      return {
        downloadLoading: false,
        autoWidth: true,
        bookType: 'xlsx'
      }
    },
    methods: {
      handleDownload() {
        this.downloadLoading = true
        import('../utils/Export2Excel').then(excel => {
          const data = this.formatJson(this.tValue, this.list)
          excel.export_json_to_excel({
            header: this.tHeader,
            data,
            filename: this.filename,
            autoWidth: this.autoWidth,
            bookType: this.bookType
          })
          this.downloadLoading = false
        })
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => {
          return v[j]
        }))
      }
    }
  }
</script>
