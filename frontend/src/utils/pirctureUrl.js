export default {
  /**
   * 根据商家ID，商品ID，图片类型得到其在前端服务器的路径
   * @param SID
   * @param GID
   * @param type
   * @returns
   */
  getPictureUrl(SID, GID, type) {
    if (type === null) {
      return null
    }
    return `@asserts/img/${SID}_${GID}.${type}`;
  }
}
