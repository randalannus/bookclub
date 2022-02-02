const monthLookup = {
  1: 'January',
  2: 'February',
  3: 'March',
  4: 'April',
  5: 'May',
  6: 'June',
  7: 'July',
  8: 'August',
  9: 'September',
  10: 'October',
  11: 'November',
  12: 'December'
}

export default {
  formatAuthorName(authorId, authorName) {
    if (!authorId) {
      return 'anonymous'
    } else if (!authorName) {
      return 'unnamed author'
    }
    return authorName
  },
  formatDate(date) {
    var year = date.getUTCFullYear()
    var month = monthLookup[date.getUTCMonth() + 1]
    var day = date.getUTCDay()
    return `${month} ${day}, ${year}`
  }
}
