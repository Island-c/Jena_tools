package formatTrans;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;


public class formatTrans
{
	public static void main(String args[])
	{
		//�˴����ļ��Ƕ�ȡ���ļ�����ȫ·����������RDF�Ĳ�ͬ�洢��ʽ��
        //String inputFileName = "F:\\database\\pdd_nt\\a.nt";
		//String inputFileName = "F:\\database\\pdd_nt\\age_gender.nt";
		String inputFileName = "F:\\database\\LOL\\kglol.rdf";
		
		
		Model model = ModelFactory.createDefaultModel();
 
		InputStream in =FileManager.get().open(inputFileName);
		if (in == null) 
		{
			throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}
 
		
		//���ݶ�ȡ�ļ��ĸ�ʽ������read�����Ĳ�����Ԥ�����ֵ�У�"RDF/XML","RDF/XML-ABBREV","N-TRIPLE","N3"��Ĭ��ֵ������""(��ֵ)����Ӧ�ĸ�ʽΪ��RDF/XML.
        model.read(in, "","RDF/XML");
		//model.read(in, "","N3");
		//model.read(in, "","TTL");
 
		// ʹ�õ���������
		StmtIterator iter = model.listStatements();
		int i=0; //�����������
		while (iter.hasNext()) 
		{
			Statement stmt = iter.nextStatement(); // get next statement
			
			
			//Resource subject = stmt.getSubject(); // get the subject
			//Property predicate = stmt.getPredicate(); // get the predicate
			//RDFNode object = stmt.getObject(); // get the object
 
			String subject = stmt.getSubject().toString(); // get the subject
			String predicate = stmt.getPredicate().toString(); // get the predicate
			RDFNode object = stmt.getObject(); // get the object
			
			String[] sub = subject.split("#");
			//String sub_one=sub[sub.length-1];
			//sub=sub_one.split("/");
			System.out.print("����� " + sub[sub.length-1]);	
			String[] pre = predicate.split("#");
			System.out.print("  ��ν� " + pre[pre.length-1]);	
			
			//����������ı���Ҳ������ʵ��
			if (object instanceof Resource) 
			{
				String[] obj=object.toString().split("#");
				System.out.print("  ����� " + obj[obj.length-1]);
			}
			else {// object is a literal
				String[] obj=object.toString().split("E");
				System.out.print("  ����� \"" + obj[0] + "\"");
			}
			System.out.println(""); 
			
			if(i++ ==500)
				//��ȡʮ������....
				return;
		}
	}
}