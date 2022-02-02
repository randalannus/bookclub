export default {
  authorName(book) {
    if (!book.authorId) {
      return 'anonymous'
    } else if (!book.authorName) {
      return 'unnamed author'
    }
    return book.authorName
  }
}
