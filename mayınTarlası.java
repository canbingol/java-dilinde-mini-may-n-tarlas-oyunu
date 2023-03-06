public class mayınTarlası {
	boolean game = true;

	int satır, sütun;
	int[][] map;
	int[][] board;
	int size = satır * sütun;
	Random rd = new Random();
	Scanner scan = new Scanner(System.in);

	mayınTarlası(int satır, int sütun) {
		this.sütun = sütun;
		this.satır = satır;
		this.map = new int[satır][sütun];
		this.board = new int[satır][sütun];
		this.size = satır * sütun;
	}

	public void run() {
		int row;
		int basarı = 0;
		int col;
		prepareGame();
		print(map);
		System.out.println();
		System.out.println();

		while (game) {
			print(board);
			System.out.println("satir sayisi :");
			row = scan.nextInt();
			System.out.println("sutun sayisi ");
			col = scan.nextInt();
			if (row < 0 || row >= satır) {
				System.out.println("gecersiz kordinat  yeniden dene ");
				continue;
			}
			if (col < 0 || col >= sütun) {
				System.out.println("gecersiz kordinat  yeniden dene ");

				continue;

			}
			// oluşturulan mayın sayısı kadar doğru tahmin yapılınca oyun sona erer
			if (map[row][col] != -1) {
				check(row, col);
				basarı++;
				if (basarı == (size - (size / 4))) {
					System.out.println("basardiniz hic bir mayina basmadiniz");

					break;
				}
			} else {
				game = false; // mayına basılmış ise oyun biter
				System.out.println("game over !");

			}
		}
	}

// oyun gereği girilen kordinatın sağını solunu yukarsını ve aşağısını kontrol ediliyor
// etrafında mayın olmayan noktalara 0 olan noktalara mayın başı 1 puan verildi 
	public void check(int r, int c) {
		if (map[r][c] == 0) {
			if ((c < satır - 1) && (map[r][c + 1] == -1)) {
				board[r][c]++;
			}
			if ((c > 0) && (map[r][c - 1] == -1)) {
				board[r][c]++;
			}
			if ((r < sütun - 1) && (map[r + 1][c] == -1)) {
				board[r][c]++;
			}
			if ((r > 0) && (map[r - 1][c] == -1)) {
				board[r][c]++;
			}
			if (board[r][c] == 0) {
				board[r][c] = -2;
			}

		}
	} // oluşturulan alanın 1/4 ü kadar rastgele mayın yerleştiriliyor

	public void prepareGame() {
		int randSatır, randSütun;
		int sayaç = 0;
		while (sayaç != (satır * sütun / 4)) {
			randSatır = rd.nextInt(satır);
			randSütun = rd.nextInt(sütun);
			if (map[randSatır][randSütun] != -1) { // aynı noktaya mayın yerleştirilmememsi için yazıldı
				map[randSatır][randSütun] = -1;
				sayaç++;
			}
		}

	}

	public void print(int arr[][]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				if (arr[i][j] >= 0) {
					System.out.print(" ");
				}
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

}
