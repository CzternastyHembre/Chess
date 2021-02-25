package chess;


public class Game2 {
	public static void main(String[] args) {
		Game b = new Game(8);
		b.init8();
		System.out.println(b);
		b.checkMate();
//		System.out.println(b.getBoard()[7][1].getPath());
//		System.out.println(b.getBoard()[1][1].getPath());
//		b.getBoard()[6][0].moveTo(0, 4);
//		b.getBoard()[1][1].moveTo(1, 3);
//		System.out.println(b);
//		System.out.println(b.getBoard()[4][0].getPath());
//		System.out.println(b.getBoard()[3][1].getPath());
//		b.getBoard()[4][0].moveTo(1, 3);
//		System.out.println(b);
//		b.getBoard()[3][1].moveTo(1, 2);
//		b.getBoard()[2][1].moveTo(1, 1);
//		System.out.println(b);
//		
//		System.out.println(b);
//		System.out.println(b.getBoard()[0][1].getPath());
//		b.getBoard()[0][1].moveTo(2, 2);
//		
//		System.out.println(b);
//		System.out.println(b.getBoard()[2][2].getPath());
//		b.getBoard()[2][2].moveTo(0, 3);
//		
//		System.out.println(b);
//		System.out.println(b.getBoard()[3][0].getPath());
//		b.getBoard()[3][0].moveTo(1, 1);
//
//		System.out.println(b);
//		System.out.println(b.getBoard()[1][1].getPath());
		
//		System.out.println(b.getPiece(2, 7).getPath());
//		b.getPiece(3, 6).moveTo(3, 4);
//		b.getPiece(1, 6).moveTo(1, 4);
//		System.out.println(b);
//		System.out.println(b.getPiece(2, 7).getPath());
//
//		b.getPiece(2, 7).moveTo(5, 4);
//		System.out.println(b);
//		System.out.println(b.getPiece(5, 4).getPath());
		
//		System.out.println(b.getPiece(0, 0).getPath());
//		b.getPiece(0, 1).moveTo(0, 3);
//		System.out.println(b);
//		System.out.println(b.getPiece(0, 0).getPath());
//		
//		b.getPiece(0, 0).moveTo(0, 2);
//		System.out.println(b);
//		System.out.println(b.getPiece(0, 2).getPath());
//		
//		b.getPiece(0, 2).moveTo(4, 2);
//		System.out.println(b);
//		System.out.println(b.getPiece(4, 2).getPath());

//		System.out.println(b.getPiece(3, 0).getPath());
//		b.getPiece(3, 1).moveTo(3, 3);
//		System.out.println(b);
//		System.out.println(b.getPiece(3, 0).getPath());
//
//		b.getPiece(3, 0).moveTo(3, 2);
//		System.out.println(b);
//		System.out.println(b.getPiece(3, 2).getPath());
//		
//		System.out.println(b.getPiece(4, 1).getPath());
//		b.getPiece(4, 1).moveTo(4, 3);
//		System.out.println(b);
//		System.out.println(b.getPiece(4, 0).getPath());
//
//		b.getPiece(4, 0).moveTo(4, 1);
//		System.out.println(b);
//		System.out.println(b.getPiece(4, 1).getPath());
		
//		b.moveFromStringLongAN("f2f3");
//		System.out.println(b);
//		b.moveFromStringLongAN("e7e5");
//		System.out.println(b);
//		b.moveFromStringLongAN("g2g4");
//		System.out.println(b);
//		b.moveFromStringLongAN("d8h4");
//		System.out.println(b);

//		b.movesFromStringLongLAN("f2f3 e7e5 g2g4 d8h4");
//		System.out.println(b);
		
//e4 e5 Nf3 Nc6 Bc4 Nf6 Ng5 d5 exd5 Na5 Bb5+ c6 dxc6 bxc6 Be2 h6 Nf3 e4 Ne5
		
//		String skoleMatt = "e2e4 e7e5 g1f3 b8c6 f1c4 g8f6 f3g5 f6e4 d1h5 h8g8 h5f7";
//		b.movesFromStringLongLAN(skoleMatt);
//		System.out.println(b);
//		b.checkMate();
		
//		System.out.println(b.getAllPaths(0));
//		b.moveFromStringLongAN("e2e4");
//		System.out.println(b);
//		System.out.println(b.getAllPaths(0));
//		b.getKing(0);
//		b.moveFromStringLongAN("e7e6");
//		b.moveFromStringLongAN("e1e2");
//		System.out.println(b);
//		b.getKing(0);
//		b.getKing(1);

//		String skoleMatt1 = "e2e4 e7e5 g1f3 b8c6 f1c4 g8f6 f3g5 f6e4";
//		String skoleMatt2 = "d1h5 h8g8 h5f7";
//		b.movesFromStringLongLAN(skoleMatt1);
//		System.out.println(b);
//		System.out.println(b.checkMate());
//		
//
//		System.out.println(b);
//		b.movesFromStringLongLAN(skoleMatt2);
//		
//		System.out.println(b.checkMate());

//		b.checkMate();
//		b.checkMate();
		
//		String sjekkSjakk = "e2e4 e7e5 d1h5 b8a6 h5f7";
//		b.movesFromStringLongLAN(sjekkSjakk);
//		System.out.println(b);
//
//		String sjekkSjakk = "e2e4 e7e5 d1h5 f7f6 g7g6";
//		b.movesFromStringLongLAN(sjekkSjakk);
//		System.out.println(b.getPiece(0, 1).getPath());
		
//		String veryLongGame = "d2d4 g8f6 c2c4 e7e6 g1f3 b7b6 a2a3 c8b7 b1c3 d7d5 c4d5 f6d5 d1c2 d5c3 b2c3 c7c5 e2e4 b8d7 c1f4 c5d4 c3d4 a8c8 c2b3 f8e7 f1d3 d7f6 b3b5 d8d7 f3e5 d7b5 d3b5 e8f8 f2f3 f6e8";
//		b.movesFromStringLongLAN(veryLongGame);
		System.out.println(b.getLANArr());
	}

}
