package fsync

class FolderSyncSuite extends munit.FunSuite:
  test("numbers") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  }

  test("home") {
    assertEquals(sync(), "/home/charles")
  }
