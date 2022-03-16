package base;

import java.io.*;

public class TextNote extends Note{
	String content;

	public TextNote(String title){
		super(title);
	}

	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}

	public TextNote(File file){
		super(file.getName());
		this.content = getTextFromFile(file.getAbsolutePath());
	}

	public String getTextFromFile(String absolutePath){
		String result = "";

		try{
			FileInputStream fis = new FileInputStream(absolutePath);
			InputStreamReader in = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(in);
			StringBuilder sb = new StringBuilder();
			result = reader.readLine();

			while(!result.isEmpty()){
				sb.append(result);
				result = reader.readLine();
			}

			result = sb.toString();
			reader.close();
			in.close();
			fis.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public void exportTextToFile(String pathFolder){
		try{
			String folder = this.getTitle().replace(" ", "_");
			File file;
			if(pathFolder.isEmpty())
				file = new File(folder + ".txt");
			else
				file = new File(pathFolder + File.separator + folder + ".txt");
			FileWriter fwriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(fwriter);
			writer.write(this.content);
			writer.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
