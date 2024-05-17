import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    List<FiguraGeometrica> figuras = new ArrayList<>();

    public void menu(){
        int op = 0;
        do{
            System.out.println("---MENU---");
            System.out.println("1. Pentagono");
            System.out.println("2. Hexagono");
            System.out.println("3. Paralelogramo");
            System.out.println("4. Rombo");
            System.out.println("5. Filtrar area de hexagonos mayores a 20");
            System.out.println("6. Promedio de los perimetros de los hexagonos");
            System.out.println("7. Figuras con perimetro y area maxima y minima");
            System.out.println("8. Promedio de los perimetros de pentagnos y hexagonos");
            op = scanner.nextInt();
            switch (op) {
                case 1: 
                    Pentagono();
                    break;
                case 2:
                    Hexagono();
                    break;
                case 3:
                    Paralelogramo();
                    break;
                case 4:
                    Rombo();
                    break;
                case 5:
                    filtrarHexagonosPorArea();
                    break; 
                case 6:
                    promedioPerimetrosHexagonos();
                    break;
                case 7:
                    figuraConMaxMinPerimetroYArea();
                    break;
                case 8:
                    promedioPerimetrosPentagonoHexagono();
                    break;                
                default:
                    System.out.println("Elija una opcion valida");
                    break;
            }
        }while (op != 0);
    }

    private double validarEntrada() {
        double valor;
        while (true) {
            if (scanner.hasNextDouble()) {
                valor = scanner.nextDouble();
                if (valor > 0) {
                    break;
                } else {
                    System.out.print("El valor debe ser positivo. Intente de nuevo: ");
                }
            } else {
                System.out.print("Entrada inválida. Intente de nuevo: ");
                scanner.next();
            }
        }
        return valor;
    }

        public void Pentagono() {
            System.out.print("Ingrese el lado del pentágono: ");
            double ladoPentagono = validarEntrada();
            Function<Double, Double> areaPentagono = L -> (5.0 / 4.0) * Math.pow(L, 2) * (1.0 / Math.tan(Math.PI / 5.0));
            Function<Double, Double> perimetroPentagono = L -> 5 * L;
            FiguraGeometrica pentagono = new FiguraGeometrica("Pentágono", areaPentagono.apply(ladoPentagono), perimetroPentagono.apply(ladoPentagono));
            System.out.println("Pentágono: " + pentagono);
            figuras.add(pentagono);
        }

        public void Hexagono() {
            System.out.print("Ingrese el lado del hexágono: ");
            double ladoHexagono = validarEntrada();
            Function<Double, Double> areaHexagono = L -> (3 * Math.sqrt(3) / 2) * Math.pow(L, 2);
            Function<Double, Double> perimetroHexagono = L -> 6 * L;
            FiguraGeometrica hexagono = new FiguraGeometrica("Hexágono", areaHexagono.apply(ladoHexagono), perimetroHexagono.apply(ladoHexagono));
            System.out.println("Hexágono: " + hexagono);
            figuras.add(hexagono);
        }

        public void Paralelogramo() {
            System.out.print("Ingrese la base del paralelogramo: ");
            double baseParalelogramo = validarEntrada();
            System.out.print("Ingrese la altura del paralelogramo: ");
            double alturaParalelogramo = validarEntrada();
            System.out.print("Ingrese el lado del paralelogramo: ");
            double ladoParalelogramo = validarEntrada();
            Function<double[], Double> areaParalelogramo = params -> params[0] * params[1];
            Function<double[], Double> perimetroParalelogramo = params -> 2 * (params[0] + params[2]);
            double areaParalelogramoValor = areaParalelogramo.apply(new double[]{baseParalelogramo, alturaParalelogramo, ladoParalelogramo});
            double perimetroParalelogramoValor = perimetroParalelogramo.apply(new double[]{baseParalelogramo, alturaParalelogramo, ladoParalelogramo});
            FiguraGeometrica paralelogramo = new FiguraGeometrica("Paralelogramo", areaParalelogramoValor, perimetroParalelogramoValor);
            System.out.println("Paralelogramo: " + paralelogramo);
            figuras.add(paralelogramo);
        }

        public void Rombo() {
            System.out.print("Ingrese la diagonal mayor del rombo: ");
            double diagonalMayorRombo = validarEntrada();
            System.out.print("Ingrese la diagonal menor del rombo: ");
            double diagonalMenorRombo = validarEntrada();
            System.out.print("Ingrese el lado del rombo: ");
            double ladoRombo = validarEntrada();
            Function<double[], Double> areaRombo = params -> (params[0] * params[1]) / 2;
            Function<Double, Double> perimetroRombo = L -> 4 * L;
            double areaRomboValor = areaRombo.apply(new double[]{diagonalMayorRombo, diagonalMenorRombo});
            double perimetroRomboValor = perimetroRombo.apply(ladoRombo);
            FiguraGeometrica rombo = new FiguraGeometrica("Rombo", areaRomboValor, perimetroRomboValor);
            System.out.println("Rombo: " + rombo);
            figuras.add(rombo);
        }

        public void filtrarHexagonosPorArea() {
            System.out.println("Hexágonos con área mayor a 20:");
            figuras.stream().filter(figura -> figura.getNombre().equals("Hexágono") && figura.getArea() > 20).forEach(hexagono -> System.out.println(hexagono.getNombre() + ": " + hexagono.getArea()));
        }

        public void promedioPerimetrosHexagonos() {
            double promedio = figuras.stream().filter(figura -> figura.getNombre().equals("Hexágono")).mapToDouble(FiguraGeometrica::getPerimetro).average().orElse(0);
            System.out.println("Promedio de perímetros de los hexágonos: " + promedio);
        }

        public void figuraConMaxMinPerimetroYArea() {
            // figura con maximo y minimo perimetro
            FiguraGeometrica figuraPerimetroMax = figuras.stream().max(Comparator.comparingDouble(FiguraGeometrica::getPerimetro)).orElse(null);
        
            FiguraGeometrica figuraPerimetroMin = figuras.stream().min(Comparator.comparingDouble(FiguraGeometrica::getPerimetro)).orElse(null);
        
            // figura con maximo y minimo area
            FiguraGeometrica figuraAreaMax = figuras.stream().max(Comparator.comparingDouble(FiguraGeometrica::getArea)).orElse(null);
        
            FiguraGeometrica figuraAreaMin = figuras.stream().min(Comparator.comparingDouble(FiguraGeometrica::getArea)).orElse(null);
        
            System.out.println("Perimetro maximo: " + figuraPerimetroMax);
            System.out.println("Perimetro minimo: " + figuraPerimetroMin);
            System.out.println("Area maxima: " + figuraAreaMax);
            System.out.println("Area minima: " + figuraAreaMin);
        }

        public void promedioPerimetrosPentagonoHexagono() {
            //filtro de pentagonos y hexagonos
            List<FiguraGeometrica> pentagonosHexagonos = figuras.stream()
                    .filter(figura -> figura.getNombre().equals("Pentágono") || figura.getNombre().equals("Hexágono"))
                    .collect(Collectors.toList());
        
            //promedio de los perimetros
            double promedioPerimetros = pentagonosHexagonos.stream().mapToDouble(FiguraGeometrica::getPerimetro).average().orElse(0);

            System.out.println("Promedio de los perímetros de Pentágonos y Hexágonos: " + promedioPerimetros);
        }

    static class FiguraGeometrica {
        private String nombre;
        private double area;
        private double perimetro;

        public FiguraGeometrica(String nombre, double area, double perimetro) {
            this.nombre = nombre;
            this.area = area;
            this.perimetro = perimetro;
        }

        public String getNombre() {
            return nombre;
        }

        public double getArea() {
            return area;
        }

        public double getPerimetro() {
            return perimetro;
        }

        @Override
        public String toString() {
            return nombre + " (Área: " + area + ", Perímetro: " + perimetro + ")";
        }
    }
    
}
