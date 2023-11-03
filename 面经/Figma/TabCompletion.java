// import java.util.ArrayList;
// import java.util.List;

// public class TabCompletion {

//     public static String findCommonPrefix(List<String> files, String input) {
//         List<String> result = new ArrayList<>();
//         for (String file : files) {
//             if (file.startsWith(input)) {
//                 result.add(file);
//             }
//         }
//         return result.get(0);
//     }

//     public static String findCommonPrefixInSubfolders(List<String> files, String input) {
//         String[] firstFolder = files.get(0).split("/");
//         boolean allInSameFolder = true;
//         int index = 0;
//         // 要改
//         while (allInSameFolder && index < firstFolder.length){

//             for (String file : files) {
//                 String[] cur = file.split("/");
//                     if (!file.split("/")[index].equals(firstFolder[index])) {
//                         allInSameFolder = false;
//                         break;
//                     }
//                 }
//                 index++;
            
//         }

//             List<String> result = new ArrayList<>();
//             for (String file : filteredFiles) {
//                 result.add(file.replaceFirst(firstFolder + "/", ""));
//             }
//         return filteredFiles;
//     }

//     public static String findCommonPrefixWithInputSlash(List<String> files, String input) {
//         String commonPrefix = input;
//         boolean allInSameFolder = true;
//         String[] inputParts = input.split("/");
//         String folder = inputParts.length > 1 ? inputParts[0] : null;
    
//         for (String file : files) {
//             if (!file.startsWith(commonPrefix)) {
//                 commonPrefix = commonPrefix.substring(0, commonPrefix.length() - 1);
//             }
//             if (allInSameFolder && folder != null) {
//                 String[] parts = file.split("/");
//                 if (!folder.equals(parts[0])) {
//                     allInSameFolder = false;
//                 }
//             }
//         }
    
//         if (allInSameFolder && folder != null) {
//             return folder + "/";
//         }
//         return commonPrefix;
//     }

//     public static void main(String[] args) {
//         List<String> files = List.of("file1", "file2", "file_new");
//         String input = "f";
//         System.out.println(findCommonPrefix(files, input));  // Expected output: "file"

//         List<String> files1 = List.of("folder/file1", "folder/file2", "folder/file/new");
//         String input1 = "f";
//         System.out.println(findCommonPrefixInSubfolders(files1, input1)); 

//         List<String> files2 = List.of("folder/file1", "folder/file2", "folder/file/new");
//         String input2 = "folder/f";
//         System.out.println(findCommonPrefixWithInputSlash(files2, input2));  // Expected output: "folder/file"

//     }
// }

