import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import main.java.*;

public class EnergyCheckUtils {
	public native static int scale(int freq);
	public native static int[] freqAvailable();

	public native static double[] GetPackagePowerSpec();
	public native static double[] GetDramPowerSpec();
	public native static void SetPackagePowerLimit(int socketId, int level, double costomPower);
	public native static void SetPackageTimeWindowLimit(int socketId, int level, double costomTimeWin);
	public native static void SetDramTimeWindowLimit(int socketId, int level, double costomTimeWin);
	public native static void SetDramPowerLimit(int socketId, int level, double costomPower);
	public native static int ProfileInit();
	public native static int GetSocketNum();
	public native static String EnergyStatCheck();
	public native static void ProfileDealloc();
	public native static void SetPowerLimit(int ENABLE);
	public static int wraparoundValue;

	public static int socketNum;
	static {
		System.setProperty("java.library.path", System.getProperty("user.dir"));
		System.out.println(System.getProperty("java.library.path"));
		try {
			Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
			fieldSysPath.setAccessible(true);
			fieldSysPath.set(null, null);
		} catch (Exception e) { }

		System.loadLibrary("CPUScaler");
		wraparoundValue = ProfileInit();
		socketNum = GetSocketNum();
	}

	/**
	 * @return an array of current energy information.
	 * The first entry is: Dram/uncore gpu energy(depends on the cpu architecture.
	 * The second entry is: CPU energy
	 * The third entry is: Package energy
	 */

	public static double[] getEnergyStats() {
		socketNum = GetSocketNum();
		String EnergyInfo = EnergyStatCheck();
		//System.out.println(EnergyInfo);
		/*One Socket*/
		if(socketNum == 1) {
			double[] stats = new double[3];
			String[] energy = EnergyInfo.split("#");

			stats[0] = Double.parseDouble(energy[0].replaceFirst(",","."));
			stats[1] = Double.parseDouble(energy[1].replaceFirst(",","."));
			stats[2] = Double.parseDouble(energy[2].replaceFirst(",","."));

			return stats;

		} else {
		/*Multiple sockets*/
			String[] perSockEner = EnergyInfo.split("@");
			double[] stats = new double[3*socketNum];
			int count = 0;


			for(int i = 0; i < perSockEner.length; i++) {
				String[] energy = perSockEner[i].split("#");
				for(int j = 0; j < energy.length; j++) {
					count = i * 3 + j;	//accumulative count
					stats[count] = Double.parseDouble(energy[j].replaceFirst(",","."));
				}
			}
			return stats;
		}

	}


	public static void main(String[] args) {
		double[] before;
		double[] media1 = new double[3];
		double[] media2 = new double[3];
		double[] media3 = new double[3];
		double[] after;

		for(int i= 0; i < 10; i++){
			before = getEnergyStats();
			UmCarroJaApp.initApp();
			UmCarroJaApp.lerDadosTXT("logs3.txt");
			after = getEnergyStats();
			media1[0] = media1[0] + (after[0] - before[0]);
			media1[1] = media1[1] + (after[1] - before[1]);
			media1[2] = media1[2] + (after[2] - before[2]);
		}

		System.out.println("Energy consumption of dram[LOAD]: " + (media1[0]/25));
		System.out.println("Energy consumption of cpu[LOAD]: " + (media1[1]/25));
		System.out.println("Energy consumption of package[LOAD]: " + (media1[2]/25));

		for(int i= 0; i < 10; i++){
			before = getEnergyStats();
			UmCarroJaApp.clientesComMaisKm();
			after = getEnergyStats();
			media2[0] = media2[0] + (after[0] - before[0]);
			media2[1] = media2[1] + (after[1] - before[1]);
			media2[2] = media2[2] + (after[2] - before[2]);
		}

		System.out.println("Energy consumption of dram[ORDER]: " + (media2[0]/25));
		System.out.println("Energy consumption of cpu[ORDER]: " + (media2[1]/25));
		System.out.println("Energy consumption of package[ORDER]: " + (media2[2]/25));


		for(int i= 0; i < 10; i++){
			before = getEnergyStats();
			try {
				UmCarroJa ucj = new UmCarroJa();
				Proprietario p = new Proprietario("1", "1", "1@gmail.com", "1", "Braga", new GregorianCalendar());
				ucj.registarUtilizador(p);
				Veiculo v1 = new Veiculo("Ford","AZ-12-12","1",90,3.45,1.3,100,new Coordinate(1,1),true,0,new ArrayList<>());
				Veiculo v2 = new Veiculo("Mercedes","LO-34-01","1",110,3.70,1.9,120,new Coordinate(13.2,1),true,0,new ArrayList<>());
				ucj.registarVeiculo(v1);
				ucj.registarVeiculo(v2);

				Cliente c = new Cliente();
				ucj.registarUtilizador(c);
				ucj.iniciarSessao(c.getEmail(),c.getPassword());
				ucj.maisBaratoNoPerimetro(new Coordinate(7,2),c.getPosicao(),new ParDatas(),25,1);

			}catch(UtilizadorNaoExisteException | VeiculoJaExisteException | PasswordIncorretaException | NaoExistemVeiculosDisponiveisException | UtilizadorJaExisteException e){
				System.out.println(e.getMessage());
			}

			after = getEnergyStats();
			media3[0] = media3[0] + (after[0] - before[0]);
			media3[1] = media3[1] + (after[1] - before[1]);
			media3[2] = media3[2] + (after[2] - before[2]);
		}

		System.out.println("Energy consumption of dram[Rental]: " + (media3[0]/25));
		System.out.println("Energy consumption of cpu[Rental]: " + (media3[1]/25));
		System.out.println("Energy consumption of package[Rental]: " + (media3[2]/25));
		
		ProfileDealloc();
	}
    
}
