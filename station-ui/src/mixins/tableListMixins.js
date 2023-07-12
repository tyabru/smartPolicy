export default {
  data() {
    return {
      config: {
        needPaging: true,
        axiosRequest: null
      },
      loading: false,
      total: 0,
      showSearch: true,
      pageSizes: [10, 20, 30, 40, 50, 100],
      tableData: [],
      queryParams: {
        pageNum: 1,
        pageSize: 20
      }
    }
  },
  methods: {
    initTableData(
      requestApi = () => new Promise(resolve => { resolve({data: []})}),
      handleResponse = () => {}) {
      this.axiosRequest = requestApi;
      this.handleResponse = handleResponse;
      this.changeLoading(true)
      requestApi(this.queryParams).then((response) => {
        if (response.rows || response.data) {
          this.setTableData(response.rows || response.data)
        } else {
          this.$message.warning(response.msg)
        }
        // 对Ajax请求返回的数据经i选进行自定义处理
        if (typeof handleResponse === 'function') {
          handleResponse(response)
        }
      }).finally(() => {
        this.changeLoading(false)
      })
    },
    setTableData(tableData) {
      this.tableData = tableData
      this.total = this.tableData.length
    },
    queryChanged() {
      if(!this.axiosRequest || !this.handleResponse) {
        return
      }
      this.initTableData(this.axiosRequest, this.handleResponse);
    },
    changeLoading(loading) {
      this.loading =
        typeof loading !== 'undefined' && loading !== null?
          loading:
          !this.loading;
    },
    confirm(massage, callback = () => {}, cancelCallback = () => {}) {
      this.$confirm(massage, "操作确认", {}).then(r => {
        callback();
      }).catch(() => {
        cancelCallback();
      })
    }

  }
}
