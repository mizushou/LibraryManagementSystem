package ca.ciccc.util;

public class PseudoIsbnGenerator {

    final private int MAXDIGITS = 8;

    public PseudoIsbnGenerator() {
    }

    public String generatePseudoIsbn(int edition) {

        RandomGenerator rg = new RandomGenerator();
        StringBuffer isbn = new StringBuffer("ISBN-");

        //1. Prefix part
        int prefix = rg.generateRandomInt(2) + 978;

        isbn.append(prefix);

        //2. Group part  [English only]
        int group = 0;
        isbn.append("-");
        isbn.append(group);
        isbn.append("-");

        //3. Author part & 4. Book part
        int[] arr = new int[MAXDIGITS];
        String e = String.valueOf(edition);

        for (int i = 0; i < arr.length - e.length(); i++) {
            arr[i] = rg.generateRandomInt(10);
        }
        arr[arr.length - 1] = edition;

        for (int i = 0; i < arr.length - 1; i++) {
            isbn.append(arr[i]);
        }

        isbn.append("-");
        isbn.append(arr[arr.length - 1]);
        isbn.append("-");

        //5. Check digit part
        isbn.append(genereteChkDigit(prefix, group, arr));

        return isbn.toString();

    }

    private int genereteChkDigit(int prefix, int group, int[] arr) {

        int a = prefix * 1;
        int b = group * 3;

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 != 0) arr[i] *= 3;
        }

        int sum = a + b;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        int mod = sum % 10;
        if (mod == 0)
            return 0;
        else return 10 - mod;
    }


}
