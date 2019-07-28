
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import weka.classifiers.Classifier;
import java.io.Serializable;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.FastVector;
import weka.core.Instances;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cse
 */
public class weka {
    
    public String ClassifyInstance( String[] features) throws IOException, ClassNotFoundException, Exception
    {
        //String cl="";
        ObjectInputStream ois = new ObjectInputStream(
                           new FileInputStream("Diabetes_Test_Model.model"));// load model
        Classifier classifier = (Classifier) ois.readObject();
        ois.close();
        Instance newIns = createInstance(features);// create new Instance
        double cls = classifier.classifyInstance(newIns);// classify the new instance
        System.out.print(classifier.classifyInstance(newIns));
        return Double.toString(cls);// return class
                
        
    }
    public Instance createInstance(String[] features)
    {
        System.out.println(features);
        //declaring attributes
        // deeclare numeric attribute
        FastVector myNomVals = new FastVector();
        Attribute Attribute1 = new Attribute("age");
        Attribute Attribute2 = new Attribute("ntp");
        // declare nominal attribute
        
        Attribute Attribute3 = new Attribute("pgc");
        Attribute Attribute4 = new Attribute("dbp");
        Attribute Attribute5 = new Attribute("tst");
        Attribute Attribute6 = new Attribute("si");
        Attribute Attribute7 = new Attribute("bmi");
        Attribute Attribute8 = new Attribute("dpf");
       
        FastVector fvClassVal = new FastVector(2);
        fvClassVal.addElement("positive");
        fvClassVal.addElement("negative");
        Attribute Class = new Attribute("class", fvClassVal);
        // add all the attribute in the weka attributes list
        FastVector fvWekaAttributes = new FastVector(9);
        fvWekaAttributes.addElement(Attribute1);
        fvWekaAttributes.addElement(Attribute2);
        fvWekaAttributes.addElement(Attribute3);
        fvWekaAttributes.addElement(Attribute4);
        fvWekaAttributes.addElement(Attribute5);
        fvWekaAttributes.addElement(Attribute6);
        fvWekaAttributes.addElement(Attribute7);
        fvWekaAttributes.addElement(Attribute8);

        fvWekaAttributes.addElement(Class);
        
        Instances dataset = new Instances("whatever", fvWekaAttributes, 0);
        dataset.setClassIndex(dataset.numAttributes()-1);
        Instance iExample = new DenseInstance(features.length);
        
        iExample.setValue((Attribute)fvWekaAttributes.elementAt(0), Double.parseDouble(features[0]));
        iExample.setValue((Attribute)fvWekaAttributes.elementAt(1), Double.parseDouble(features[1]));
        iExample.setValue((Attribute)fvWekaAttributes.elementAt(2), Double.parseDouble(features[2]));
        iExample.setValue((Attribute)fvWekaAttributes.elementAt(3), Double.parseDouble(features[3]));
        iExample.setValue((Attribute)fvWekaAttributes.elementAt(4), Double.parseDouble(features[4]));
        iExample.setValue((Attribute)fvWekaAttributes.elementAt(5), Double.parseDouble(features[5]));
        iExample.setValue((Attribute)fvWekaAttributes.elementAt(6), Double.parseDouble(features[6]));
        iExample.setValue((Attribute)fvWekaAttributes.elementAt(7), Double.parseDouble(features[7]));

         
        dataset.add(iExample);
        
        return dataset.instance(0);
       
        
    }
    
}
