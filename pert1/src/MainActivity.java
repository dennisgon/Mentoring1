import java.util.*;
public class MainActivity {
	String nama;
	String kendaraan;
	boolean loop = true;
	Scanner sc = new Scanner(System.in);
	Mobil mb = new Mobil();
	Truck tr = new Truck();
	int weight=0;
	int distance;
	
	public MainActivity() {
		// TODO Auto-generated constructor stub
		int input = 0;
		
		
		do {
			System.out.print("input your name [5-25]: ");
			nama = sc.nextLine();
		} while (cekNama(nama));
		
		inputKendaraan();
		
		do{
			System.out.println("1.	Drive \n2.	Refill Gas\n3.	Check Vehicle\n4.	Change vehicle\n5.	Exit");
			System.out.print("choose: ");
			input = sc.nextInt();
			
			switch (input) {
			case 1:
				drive(kendaraan);
				break;
			case 2:
				refill(kendaraan);
				break;
			case 3:
				detail(kendaraan);
				break;
			case 4:
				inputKendaraan();
				break;
			case 5:
				System.exit(0);
				break;
			}
		}while(loop == true);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainActivity main = new MainActivity();
	}
	
	//case drive
	private void drive(String kendaraan){
		if (kendaraan.equals("Car")) {
			if (mb.getFuel()<=0) {
				System.out.println("not enough fuel, please refill....");
			}else{
				int maxDistance=mb.getFuel()*15;
				System.out.println("driving information");
				System.out.println("==========================");
				System.out.print("input distance [1-"+maxDistance+"]: ");
				distance = sc.nextInt();
				
				if (distance>maxDistance) {
					System.out.println("cannot go");
				}else{
					System.out.println("driving report");
					System.out.println("==================");
					System.out.println("car speed\t:"+mb.getSpeed());
					System.out.println("fuel left:"+mb.drive(distance,15));
				}
			}
		}else{
			boolean cek = true;
			do{
				int maxDistance=tr.getFuel()*10;
				System.out.println("driving information");
				System.out.println("==========================");
				System.out.print("input distance [1-"+maxDistance+"]: ");
				distance = sc.nextInt();
				
				if (distance>maxDistance) {
					System.out.println("cannot go");
				}else{
					System.out.print("do you want to carry items?: ");
					String jawaban = sc.next();
					if (jawaban.equals("Y")) {
						cek = false;
						System.out.print("input item weight to be carried[1-1500]<kg>: ");
						tr.setWeight(sc.nextInt());
						System.out.println("driving report");
						System.out.println("==================");
						System.out.println("Carried weight: "+tr.getWeight());
						System.out.println("truck speed\t:"+tr.getSpeed());
						System.out.println("fuel left:"+tr.drive(distance,10));
					}else if(jawaban.equals("N")){
						cek = false;
						System.out.println("driving report");
						System.out.println("==================");
						System.out.println("truck speed\t:"+tr.getSpeed());
						System.out.println("fuel left:"+tr.drive(distance,10));
					}else{
						cek = true;
					}
				}
			}while(cek==true);
		}
	}
	
	//input data kendaraan
	private void inputKendaraan(){

		do{
			System.out.print("input your venhicle['Car'|'Truck']: ");
			kendaraan = sc.next();
		}while(cekKendaraan(kendaraan));
		switch (kendaraan) {
			case "Car":
				do {
					mb.setFuel(0);
					System.out.print("input your car brand's['BMX'| 'Toyoda'| 'Pissan']: ");
					mb.setBrand(sc.next());
				} while (cekBrand(mb.getBrand(), kendaraan));
				break;
			case "Truck":
				do {
					tr.setFuel(0);
					System.out.print("input your car brand's['Wercedes'|'Polpo'| 'Lord']: ");
					tr.setBrand(sc.next());
				} while (cekBrand(tr.getBrand(), kendaraan));
				
				break;
		}
	}
	
	
	//case detail
	private void detail(String kendaraan){
		if (kendaraan.equals("Car")) {
			System.out.println("Car Details:");
			System.out.println("=====================");
			System.out.println("brand\t\t: "+mb.getBrand());
			System.out.println("fuel left\t: "+mb.fuel+"\\"+mb.getMax());
		}else{
			System.out.println("Truck Details:");
			System.out.println("=====================");
			System.out.println("brand\t\t: "+tr.getBrand());
			System.out.println("fuel left\t: "+tr.getFuel()+"\\"+tr.getMax());
			System.out.println("weight\t\t: "+tr.getWeight());
		}
	}
	
	
	//case refill
	private void refill(String kendaraan){
		if (kendaraan.equals("Car")) {
			if (mb.getFuel() == mb.getMax()) {
				System.out.println("gas is already full");
			}else{
				mb.setFuel(mb.isiGas(20));
				if (mb.getFuel()>mb.getMax()) {
					mb.setFuel(20); 
				}
			}
		}else{
			if (tr.getFuel() == tr.max) {
				System.out.println("gas is already full");
			}else{
				tr.setFuel(tr.isiGas(50));
				if (tr.getFuel()>mb.getMax()) {
					tr.setFuel(50);
				}
			}
		}

	}
	
	
	//validasi brand
	private boolean cekBrand(String brand, String kendaraan){
		return (kendaraan.equals("Car"))? 
				(!brand.equals("BMX")&& !brand.equals("Toyoda")&& !brand.equals("Pissan"))?true: false
						:(!brand.equals("Wercedes")&& !brand.equals("Polpo")&& !brand.equals("Lord"))?true: false;
	}
	
	
	//validasi nama
	private boolean cekNama(String nama){	
		return (nama.length()<5||nama.length()>25)?  true:  false;
	}
	
	
	//validasi kendaraan
	private boolean cekKendaraan(String kendaraan){
		return (!kendaraan.equals("Car") && !kendaraan.equals("Truck"))? true: false;

	}

}

//class kendaraan
class Kendaraan{
	int fuel=0;
	String brand;
	int speed;
	public int getFuel() {
		return fuel;
	}
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int isiGas(int fuel){
		return fuel=+fuel;
	}
	public int getSpeed() {
		return 0;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int drive(int distance,int kendaraan){

		return fuel = fuel-(distance/kendaraan);
	}
}

//class mobil
class Mobil extends Kendaraan{
	final int max = 20;

	public int getMax() {
		return max;
	}
	@Override
	public int getSpeed() {
		int panjang= brand.length();
		int perhitunganKecepatan = (int)(((Math.random()*50)+51)+(Math.random()*panjang));
		return perhitunganKecepatan;
	}
	

}

//class truck
class Truck extends Kendaraan{
	final int max = 50;
	int weight=0;
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getMax() {
		return max;
	}
	@Override
	public int getSpeed() {
		int panjang= brand.length();
		int perhitunganKecepatan = (int) ((int)(((Math.random()*50)+36)+(Math.random()*panjang))-(0.02*weight));
		return perhitunganKecepatan;
	}
	
}
