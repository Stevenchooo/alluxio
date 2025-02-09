/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.stress.fuse;

/**
 * The operations for the FUSE stress tests.
 */
public enum FuseIOOperation {
  /** Write operation to test the write throughput or prepare data for reading. */
  WRITE("Write"),

  /**
   * List the files before reading can help cache the metadata and gain more accurate reading
   * performance, if client-side metadata cache is enabled.
   */
  LIST_FILE("ListFile"),

  /** Now only streaming reading is supported, that is, sequentially read the written files. */
  READ("Read"),
  // TODO(Shawn): Support other types of read
  ;

  /**
   * @param operation the operation
   * @return true if the operation is a read
   */
  public static boolean isRead(FuseIOOperation operation) {
    switch (operation) {
      case READ:
        return true;
      default:
        return false;
    }
  }

  private final String mName;

  FuseIOOperation(String name) {
    mName = name;
  }

  @Override
  public String toString() {
    return mName;
  }

  /**
   * Creates an instance type from the string. This method is case insensitive.
   *
   * @param text the instance type in string
   * @return the created instance
   */
  public static FuseIOOperation fromString(String text) {
    for (FuseIOOperation type : FuseIOOperation.values()) {
      if (type.toString().equalsIgnoreCase(text)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No constant with text " + text + " found");
  }
}
