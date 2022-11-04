package fsync
import scala.io.StdIn.readLine

def sync(src: os.Path, dest: os.Path) =
  val readBytes = os.read.bytes
  val copyOver = os.copy.over
  val files = os.walk(src)

  if files.isEmpty then
    os.remove.all(dest)
    os.makeDir(dest)
  else
    os.walk(src).foreach( srcSubPath =>
      val subPath = srcSubPath.subRelativeTo(src)
      val destSubPath = dest / subPath

      (os.isDir(srcSubPath), os.isDir(destSubPath)) match
        case (false, true) | (true, false) =>
          copyOver(srcSubPath, destSubPath, createFolders = true)

        case (false, false)
          if !os.exists(destSubPath) || !readBytes(srcSubPath).sameElements(readBytes(destSubPath)) =>
            copyOver(srcSubPath, destSubPath, createFolders = true)
        
        case _ => println("skipping...")
  )

@main def main() =
  print("Insert source path: ")
  val src = os.home / os.SubPath(readLine())
  print("Insert destination path: ")
  val dest = os.home / os.SubPath(readLine())

  (os.exists(src), os.exists(dest)) match
    case (false, false) =>
      println("Error: Both paths don't exist!")
      System.exit(0)
    case (false, _) =>
      println("Error: Source path doesn't exist!")
      System.exit(0)
    case (_, false) =>
      println("Error: Destination path doesn't exist!")
      System.exit(0)
    case _ => sync(src, dest)
