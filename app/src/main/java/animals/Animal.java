package animals;
public class Animal {
 private String name;
 private int edad;
 private int color;
 private int weight;
 public Animal() {
  this.name = name;
  this.edad = edad;
  this.color = color;
  this.weight = weight;
 }
 @Override
 public String toString() {
  return "Animal{" +
          "name='" + name + '\'' +
          ", edad=" + edad +
          ", color=" + color +
          ", weight=" + weight +
          '}';
 }
 public String getName() {
  return name;
 }
 public void setName(String name) {
  this.name = name;
 }
 public int getEdad() {
  return edad;
 }
 public void setEdad(int edad) {
  this.edad = edad;
 }
 public int getColor() {
  return color;
 }
 public void setColor(int color) {
  this.color = color;
 }
 public int getWeight() {
  return weight;
 }
 public void setWeight(int weight) {
  this.weight = weight;
 }

}