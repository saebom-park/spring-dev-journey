public class StringUtilMain {
    public static void main(String[] args) {
        String[] strArray = {"", "saebom", "bomi", "oni", null};
        System.out.println("isEmpty 여부 확인하기");
        for (int i = 0; i < strArray.length; i++) {
            System.out.println((i+1) + "번째 문자열");
            System.out.println("1. isEmpty? " + StringUtil.isEmpty(strArray[i]));
            if (!StringUtil.isEmpty(strArray[i])) {
                System.out.println("2. reverse? " + StringUtil.reverse(strArray[i]));
                System.out.println("3. toUpperCase? " + StringUtil.toUpperCase(strArray[i]));
                System.out.println("4. toLowerCase? " + StringUtil.toLowerCase(strArray[i]));
            }
            System.out.println("------------------------------------");
        }
    }
}
