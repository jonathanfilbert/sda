/**
 * Answer
 */

// 0011111 2
// 7

import java.io.*;
import java.util.*;

public class Answer {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] inputan = br.readLine().split(" ");
        String binaryString = inputan[0];
        int target = Integer.parseInt(inputan[1]);
        bw.write(Integer.toString(maxSub(binaryString, target)));
        bw.flush();
    }

    public static int maxSub(String biner, int target) {
        // 1110111100111110 3
        int tempJumlah = 0;
        int finalJumlah = 0;
        int zeroCount = 0;

        for (int i = 0, j = 0; j < biner.length(); j++) {
            if (biner.charAt(j) == '0') {
                zeroCount += 1;
                if (zeroCount > target) {
                    // Majuin pointer depan sampe 1 tempat setelah 0 pertama
                    while (biner.charAt(i) != '0') {
                        // Majuin pointernya
                        i += 1;
                    }
                    // Majuin 1 place after 0
                    i += 1;
                    zeroCount -= 1;
                }
            }

            tempJumlah = j - i + 1;
            if (tempJumlah > finalJumlah) {
                finalJumlah = tempJumlah;
            }
        }
        return finalJumlah;
        // TODO
        // Bikin 2 pointer, depan dan belakang
        // Iterasi pointer belakang, sambil ngitung angka 0
        // Kalo dia ketemu angka 0 setelah counternya lebih, majuin pointer depan 1
        // posisi setelah 0 pertama

        // while(string.charAt(pointer0) != 0) pointer0++;
        // pointer0++;
        // 001011110101 2
    }
}