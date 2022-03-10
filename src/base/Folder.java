package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Folder implements Comparable<Folder>{
	private ArrayList<Note> notes;
	private String name;

	public Folder(String name){
		this.name = name;
		this.notes = new ArrayList<Note>();
	}

	public void addNote(Note note){
		notes.add(note);
	}

	public String getName(){
		return this.name;
	}

	public ArrayList<Note> getNotes(){
		return this.notes;
	}

	@Override
	public String toString(){
		int nText = 0;
		int nImage = 0;

		for(int i = 0; i < notes.size(); ++i){
			if(notes.get(i) instanceof TextNote)
				++nText;
			else
				++nImage;
		}

		return name + ":" + nText + ":" + nImage;
	}

	public boolean equals(String folderName){
		return this.name.equals(folderName);
	}

	@Override
	public int compareTo(Folder o) {
		return this.name.compareTo(o.name);
	}

	public void sortNotes(){
		Collections.sort(notes);
	}

	public List<Note> searchNotes(String keywords){
		List<Note> results = new ArrayList<Note>();
		List<List<Note>> subresults = new ArrayList<List<Note>>();
		List<Note> subresult;
		Note note;
		TextNote tNote;
		//Split keywords via " " regex
		String[] conditions = keywords.toLowerCase().split(" ");


		//Looping through all keywords
		for(int i = 0; i < conditions.length; ++i){
			subresult = new ArrayList<Note>();

			if(conditions[i].equals("or")){
				subresult = subresults.get(subresults.size() - 1);
				subresults.remove(subresults.size() - 1);
				++i;
			}

			for(int j = 0; j < notes.size(); ++j){
				note = notes.get(j);
				if(note.getTitle().toLowerCase().contains(conditions[i]))
					subresult.add(note);
				else if(note.getClass().getName().equals("base.TextNote")){
					tNote = (TextNote) note;
					if(tNote.content.toLowerCase().contains(conditions[i]))
						subresult.add(note);
				}
			}
			subresults.add(subresult);
		}

		results = subresults.remove(0);

		for(int i = 0; i < results.size(); ++i){
			for(int j = 0; j < subresults.size(); ++j){
				if(!subresults.get(j).contains(results.get(i))){
					results.remove(i);
					--i;
					break;
				}
			}
		}

		return results;
	}
}
