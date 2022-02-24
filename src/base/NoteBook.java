package base;
import java.util.ArrayList;

public class NoteBook {
	private ArrayList<Folder> folders;

	public NoteBook(){
		this.folders = new ArrayList<Folder>();
	}

	public boolean createTextNote(String folderName, String title){
		TextNote note = new TextNote(title);

		return insertNote(folderName, note);
	}

	public boolean createImageNote(String folderName, String title){
		ImageNote note = new ImageNote(title);

		return insertNote(folderName, note);
	}

	public ArrayList<Folder> getFolders(){
		return this.folders;
	}

	public boolean insertNote(String folderName, Note note){
		for (int i = 0; i < this.folders.size(); ++i){
			if(folders.get(i).equals(folderName)){
				for(int j = 0; j < folders.get(i).getNotes().size(); ++j){
					if(folders.get(i).getNotes().get(j).equals(note)){
						System.out.println("Creating note " + note.getTitle() +
								" under folder " + folderName + " failed");
						return false;
					}
				}
				folders.get(i).addNote(note);
				return true;
			}
		}
		folders.add(new Folder(folderName));
		folders.get(folders.size() - 1).addNote(note);
		return true;
	}
}
