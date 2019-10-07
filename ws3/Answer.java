/**
 * Answer
 */

// 0011111 2
// 7

import java.io.*;
import java.util.*;

public class Answer {

//   INPUT
//   N T
//   NILAI N1
//   NILAI N2
//   NILAI N3
//   .....
//   T == 1
// 	Ini yang nge sort peserta
//

//   T1
//   N
// 	Nilai writing, reading, listening -> loop ini sebanyak N kali, setiap iterasi bikin object participant baru

  	static Participant[] listParticipants;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      	String[] inputLineOne = br.readLine().split(" ");
      	int jumlahParticipant = Integer.parseInt(inputLineOne[0]);
        int tipeSoal = Integer.parseInt(inputLineOne[1]);
      	listParticipants = new Participant[jumlahParticipant];
      	for(int i = 0; i<jumlahParticipant;i++){
          String[] nilai = br.readLine().split(" ");
          int writing = Integer.parseInt(nilai[0]);
          int reading = Integer.parseInt(nilai[1]);
          int listening = Integer.parseInt(nilai[2]);
          Participant baru = new Participant(i+1,writing,reading,listening);
          listParticipants[i] = baru;
        }
//       1,5,2,4,11,3
//       1 < 5, 2, 4, 11, 3> indexMax = 1
      // 1 < 5, 2, 4, 11, 3> indexMax = 1
      // 1 < 5, 2, 4, 11, 3> indexMax = 1
      // 1 < 5, 2, 4, 11, 3> indexMax = 4
      // 1 < 5, 2, 4, 11, 3> indexMax = 4
      // Swap index 0 dengan indexMax
//       Selection Sort
//       Iterate dari awal sampe abis
    // for dalam for
	// dari pointer sekarang + 1 sampe akhir
	// terbesarFinal = pointerKedua+1, terbesarTemp = arr[pointerKedua]
      int tempMax = 0;
      for(int i = 0; i<listParticipants.length;i++){
        int maxIndex = i;
        for(int j = i+1; j<listParticipants.length;j++){
          if(listParticipants[j].compareTo(listParticipants[maxIndex]) == -1){
            maxIndex = j;
          }
        }
        // Swap arr[i] dengan arr[maxIndex]
        Participant tempA = listParticipants[i];
        Participant tempB = listParticipants[tempMax];
        listParticipants[i] = tempB;
        listParticipants[tempMax] = tempA;
        listParticipants[i].swap();
        listParticipants[tempMax].swap();
      }
      if(tipeSoal == 1){
        for(int i = 0; i<listParticipants.length;i++){
          bw.write(Integer.toString(listParticipants[i].getUrutan()));
          bw.write("\n");
        }
      }
      else{ // Mapping : urutan peserta ke jumlah swap
        HashMap<Integer,Integer> peserta = new HashMap<Integer,Integer>();
        for(int i = 0; i<listParticipants.length;i++){
          peserta.put(listParticipants[i].getUrutan(),listParticipants[i].getJumlahSwap());
        }
        for(int i = 1; i<=listParticipants.length;i++){
          bw.write(Integer.toString(peserta.get(i)));
          bw.write("\n");
        }
      }
      bw.flush();
    }
} // if(participant[i].getUrutan() == 1)

class Participant implements Comparable<Participant>{
  private int nilaiFinal;
  private int jumlahSwap;
  private int urutan;
  public Participant(int urutan, int nilaiWriting, int nilaiReading, int nilaiListening){
	this.nilaiFinal = nilaiWriting*1000 + nilaiReading*100 + nilaiListening*10;
    this.urutan = urutan;
  }

  public int getUrutan(){
    return this.urutan;
  }

  public int getJumlahSwap(){
    return this.jumlahSwap;
  }

  public void swap(){
    this.jumlahSwap+=1;
  }
  // kalo kelas yang sama tapi instance beda, masi bisa manggil private yg lainnya
  @Override
  public int compareTo(Participant other){
    	if(this.nilaiFinal > other.nilaiFinal){
          return -1;
        }
    else if(this.nilaiFinal == other.nilaiFinal){
      return 0;
    }
    else{
      return 1;
    }
  }
}
