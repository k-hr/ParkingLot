// Main class : accepts input and call parser class

class Main {
  public static void main(String[] args) {
    // System.out.println(args);
    InputParser inputParser = new InputParser();
    // since path is "./input.txt"
    try{
      inputParser.parseFileInput("./input.txt");
    }catch(Exception e){
      System.out.println("Input file path is Wrong or Input file is missing in parent directory");
    }
  }
}