# Attention OCR in Java
Using Tensorflow Java bindings to run the Attention OCR model in Java. 

*Note* The jni.tar.gz file is named tensorflow 1.14 but it is actually the tensorflow 2.4.0 version hence the newer experimental version.


**Disclaimer:** The frozen graph included is not trained as of now (hence the output will be garbage) and this repository is only for providing a working code to run inference on this model in Java. The frozen graph file will be updated when it has been trained after a few weeks.

## Installation :
#### Clone and extract JNI folder
```
git clone https://github.com/wolf-hash/Attention-OCR-in-Java.git
cd Tensorflow-Java-AOCR
tar -zxvf jni.tar.gz
```
**For Windows** Extract the jni.tar.gz manually into a folder named *jni*.

It runs inference on the image placed in the *images* folder 
#### Compile and Run
```
javac -cp libtensorflow-1.14.0.jar aocr_java.java 
java -cp libtensorflow-1.14.0.jar:. -Djava.library.path=./jni aocr_java
```
