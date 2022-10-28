package fsync

@main def sync() =
  println(home)
  home

lazy val home: String = os.home.toString
