export default {
  data() {
    return {
      config: {
        idPropName: 'id',
        needPaging: true,
        axiosRequest: null
      },
      btnVisible: false,
      loading: false,
      total: 0,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
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
          this.total = response.total
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
    },
    resetQuery() {
      this.queryParams.pageNum = 1
      this.resetForm("queryForm");
      this.queryChanged();
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item[this.config.idPropName])
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
  }
}
