package com.tista.file.pusher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class PhaseIIFileDropHelper {

	/**
	 * establishes connection to the cloud server and drops the file in a
	 * sub-directory
	 * 
	 * @param user
	 * @param host
	 * @param privateKey
	 * @param fileName
	 * @return
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		new PhaseIIFileDropHelper().runShellForPhaseTwo("ec2-user", "52.207.215.189", "test",
				"customMatchingNppesInd1-prabhu.csv", "", "", "", "nppes");
	}

	private static final Logger LOGGER = Logger.getLogger(PhaseIIFileDropHelper.class.getName());

	public String runShellForPhaseTwo(String user, String host, String privateKey, String fileName1, String fileName2,
			String fileName3, String fileName4, String subDirectory) {
		String outcome = "";

		File file1 = (fileName1.equals("")) ? null : new File("data/inputData/" + subDirectory + "/" + fileName1);
		File file2 = (fileName2.equals("")) ? null : new File("data/inputData/" + subDirectory + "/" + fileName2);
		File file3 = (fileName3.equals("")) ? null : new File("data/inputData/" + subDirectory + "/" + fileName3);
		File file4 = (fileName4.equals("")) ? null : new File("data/inputData/" + subDirectory + "/" + fileName4);
		try {
			/**
			 * adding the attributes to jsch to establish connection
			 */
			Session session = null;
			JSch jsch = new JSch();
			int port = 22;
			if (privateKey != null) {
				jsch.addIdentity("aps_instance.pem");
				JSch.setConfig("StrictHostKeyChecking", "no");
				session = jsch.getSession(user, host, port);
			} else {
				session = jsch.getSession(user, host);
				session.setPort(port);
				session.setPassword(FileUtils.readFileToString(new File("aps_instance.pem"), Charset.defaultCharset()));
			}

			session.connect();

			/**
			 * creates an sftp session to transfer the file to the root dir
			 */
			Channel channel1 = session.openChannel("sftp");
			channel1.connect();

			ChannelSftp c = (ChannelSftp) channel1;

			try {
				if (file1 != null)
					c.put(new FileInputStream(file1), file1.getName());
				if (file2 != null)
					c.put(new FileInputStream(file2), file2.getName());
				if (file3 != null)
					c.put(new FileInputStream(file3), file3.getName());
				if (file4 != null)
					c.put(new FileInputStream(file4), file4.getName());

			} catch (IOException e) {
				LOGGER.info(e.getMessage());
			}
			c.exit();
			channel1.disconnect();

			/**
			 * creates an ssh session to run shell commands on the remote server
			 */
			Channel channel = session.openChannel("shell");
			LOGGER.info("shell channel connected....");
			OutputStream ops = channel.getOutputStream();
			PrintStream ps = new PrintStream(ops, true);
			String stageFolder = "stage-files";
			String command0 = null;
			String command1 = null;
			String command2 = null;
			String command3 = null;
			String command4 = null;
			if (file4 != null && file3 != null && file2 != null && file1 != null) {
				command0 = "sudo su - ec2-user";
				command1 = "sudo mv " + fileName1 + " " + fileName2 + " " + fileName3 + " " + fileName4 + " /home/nifi/"
						+ stageFolder + "/";
				command2 = "sudo chown nifi:nifi /home/nifi/" + stageFolder + "/" + fileName1 + " " + fileName2 + " "
						+ fileName3 + " " + fileName4;
				command3 = "sudo su - nifi";
				int length = fileName1.length();
				String extension = fileName1.substring(length - 3, length);
				if (extension.equals("xml")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.xml && " + "cat /home/nifi/" + stageFolder + "/" + fileName2 + ">" + " /home/nifi/"
							+ subDirectory + "/y.xml && " + "cat /home/nifi/" + stageFolder + "/" + fileName3 + ">"
							+ " /home/nifi/" + subDirectory + "/z.xml && " + "cat /home/nifi/" + stageFolder + "/"
							+ fileName4 + ">" + " /home/nifi/" + subDirectory + "/w.xml";
				} else if ((extension).equals("csv")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.csv && " + "cat /home/nifi/" + stageFolder + "/" + fileName2 + ">" + " /home/nifi/"
							+ subDirectory + "/y.csv && " + "cat /home/nifi/" + stageFolder + "/" + fileName3 + ">"
							+ " /home/nifi/" + subDirectory + "/z.csv && " + "cat /home/nifi/" + stageFolder + "/"
							+ fileName4 + ">" + " /home/nifi/" + subDirectory + "/w.csv";
				} else if ((extension).equals("txt")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/" + fileName1 + " && " + "cat /home/nifi/" + stageFolder + "/" + fileName2 + ">"
							+ " /home/nifi/" + subDirectory + "/" + fileName2 + " && " + "cat /home/nifi/" + stageFolder
							+ "/" + fileName3 + ">" + " /home/nifi/" + subDirectory + "/" + fileName3 + " && "
							+ "cat /home/nifi/" + stageFolder + "/" + fileName4 + ">" + " /home/nifi/" + subDirectory
							+ "/" + fileName4;
				}

			} else if (file3 != null && file2 != null && file1 != null) {
				command0 = "sudo su - ec2-user";
				command1 = "sudo mv " + fileName1 + " " + fileName2 + " " + fileName3 + " /home/nifi/" + stageFolder
						+ "/";
				command2 = "sudo chown nifi:nifi /home/nifi/" + stageFolder + "/" + fileName1 + " " + fileName2 + " "
						+ fileName3;
				command3 = "sudo su - nifi";
				int length = fileName1.length();
				String extension = fileName1.substring(length - 3, length);
				if (extension.equals("xml")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.xml && " + "cat /home/nifi/" + stageFolder + "/" + fileName2 + ">" + " /home/nifi/"
							+ subDirectory + "/y.xml && " + "cat /home/nifi/" + stageFolder + "/" + fileName3 + ">"
							+ " /home/nifi/" + subDirectory + "/z.xml";
				} else if ((extension).equals("csv")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.csv && " + "cat /home/nifi/" + stageFolder + "/" + fileName2 + ">" + " /home/nifi/"
							+ subDirectory + "/y.csv && " + "cat /home/nifi/" + stageFolder + "/" + fileName3 + ">"
							+ " /home/nifi/" + subDirectory + "/z.csv";
				} else if ((extension).equals("txt")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.txt && " + "cat /home/nifi/" + stageFolder + "/" + fileName2 + ">" + " /home/nifi/"
							+ subDirectory + "/y.txt && " + "cat /home/nifi/" + stageFolder + "/" + fileName3 + ">"
							+ " /home/nifi/" + subDirectory + "/z.txt";
				}
			} else if (file2 != null && file1 != null) {
				command0 = "sudo su - ec2-user";
				command1 = "sudo mv " + fileName1 + " " + fileName2 + " /home/nifi/" + stageFolder + "/";
				command2 = "sudo chown nifi:nifi /home/nifi/" + stageFolder + "/" + fileName1 + " " + fileName2;
				command3 = "sudo su - nifi";
				int length = fileName1.length();
				String extension = fileName1.substring(length - 3, length);
				if (extension.equals("xml")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.xml && " + "cat /home/nifi/" + stageFolder + "/" + fileName2 + ">" + " /home/nifi/"
							+ subDirectory + "/y.xml";
				} else if ((extension).equals("csv")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.csv && " + "cat /home/nifi/" + stageFolder + "/" + fileName2 + ">" + " /home/nifi/"
							+ subDirectory + "/y.csv";
				} else if ((extension).equals("txt")) {
					System.out.println("Dropping 4 files");
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.txt && " + "cat /home/nifi/" + stageFolder + "/" + fileName2 + ">" + " /home/nifi/"
							+ subDirectory + "/y.txt";
				}
			} else if (file1 != null) {
				command0 = "sudo su - ec2-user";
				command1 = "sudo mv " + fileName1 + " /home/nifi/" + stageFolder + "/";
				command2 = "sudo chown nifi:nifi /home/nifi/" + stageFolder + "/" + fileName1;
				command3 = "sudo su - nifi";
				int length = fileName1.length();
				String extension = fileName1.substring(length - 3, length);
				if (extension.equals("xml")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.xml";
				} else if ((extension).equals("csv")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.csv";
				} else if ((extension).equals("txt")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.txt";
				} else if ((extension).equals("lsx")) {
					command4 = "cat /home/nifi/" + stageFolder + "/" + fileName1 + ">" + " /home/nifi/" + subDirectory
							+ "/x.xlsx";
				}

			}
			channel.connect();
			ps.println(command0);
			ps.println(command1);
			ps.println(command2);
			ps.println(command3);
			ps.println(command4);

			InputStream in = channel.getInputStream();
			byte[] home = new byte[1024];

			while (in.available() > 0) {
				int i = in.read(home, 0, 1024);
				if (i < 0)
					channel.disconnect();
			}

			if (channel.isClosed()) {
				LOGGER.info("exit-status: " + channel.getExitStatus());
				// the loop needs to break out to print
			}

			insertWait();
			outcome = "File Transfered";

			channel.disconnect();
			session.disconnect();

		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}

		return outcome;
	}

	public static void insertWait() {

		try {
			Thread.sleep(1000);
		} catch (Exception ee) {
			LOGGER.log(Level.INFO, "Thread Exception", ee.getMessage());
		}
	}

}