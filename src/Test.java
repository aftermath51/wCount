public class Test {

    public boolean Test_CharCount(int expResult, String path) {
        return PrintErrorDetail(GetMethodName(), expResult, Main.ReadChar(path));
    }

    public boolean Test_WordCount(int expResult, String path) {
        return PrintErrorDetail(GetMethodName(), expResult, Main.ReadWord(path));
    }

    public boolean Test_LineCount(int expResult, String path) {
        return PrintErrorDetail(GetMethodName(), expResult, Main.ReadLine(path));
    }

    public boolean Test_ReadDiffLine(int emptyLine, int noteLine, int codeLine, String path) {
        int[] expResult = new int[3];
        expResult[0] = codeLine;
        expResult[1] = emptyLine;
        expResult[2] = noteLine;

        String[] lineNames = {"codeLine", "emptyLine", "noteLine"};

        int[] testResult = Main.GetDifferentLine(path);
        for (int i = 0; i < 3; i++) {
            PrintErrorDetail(GetMethodName() + " " + lineNames[i], expResult[i], testResult[i]);
            if (expResult[i] != testResult[i])
                return false;
        }
        return true;
    }

    public boolean Test_StopList(int expResult, String stoplistPath, String readFilePath) {
        int testResult = Main.StopWordTable(stoplistPath, readFilePath);
        return PrintErrorDetail(GetMethodName(), expResult, testResult);
    }

    public boolean Test_Recursion(int expFileCount, String path, String formateName) {
        Main.fomatName = formateName;
        return PrintErrorDetail(GetMethodName(), expFileCount, Main.FindFile(path));
    }

    public boolean Test_OutputFile(StringBuilder sb, String outputPath) {
        System.out.println("test: " + GetMethodName());
        if (Main.OutPutFile(outputPath, sb)) {
            System.out.println("out put file success");
            return true;
        }
        System.out.println("out put file failed");
        return false;
    }

    public boolean Test_Recursion_StopList(String formatName, String stopListName, int[] expCount) {
        Main.fomatName = formatName;
        Main.FindFile("./");
        boolean result = true;
        for (int i = 0; i < Main.canBeFoundFile.size(); i++) {
            if (!Test_StopList(expCount[i], stopListName, Main.canBeFoundFile.get(i))) {
                result = false;
            }
        }

        return result;
    }

    public boolean Test_Recusion_WordRead_OutputFile(String formatName, String outputFileName, int[] expResult) {
        Main.fomatName = formatName;
        Main.FindFile(Main.fileDir);
        boolean result = true;

        for (int i = 0; i < Main.canBeFoundFile.size(); i++) {
            if (expResult[i] != Main.ReadWord(Main.canBeFoundFile.get(i))) {
                result = false;
            }
        }
        if (result) {
            result = Main.OutPutFile(outputFileName, Main.sb);
        }
        return result;
    }

    private static String GetMethodName() {
        String funName = new Throwable().getStackTrace()[1].getMethodName();
        return funName;
    }

    private boolean PrintErrorDetail(String testName, int expResult, int testResult) {
        System.out.println("test: " + testName);
        System.out.println("exp result: " + expResult);
        System.out.println("test result: " + testResult);
        if (expResult == testResult)
            return true;
        return false;
    }
}
