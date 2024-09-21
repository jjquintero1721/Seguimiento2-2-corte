package Punto2.aplication;

import Punto2.aplication.service.EmpleadoService;
import Punto2.aplication.service.EmpleadoServiceImpl;
import Punto2.aplication.service.TareaService;
import Punto2.aplication.service.TareaServiceImpl;
import Punto2.domain.Empleado;
import Punto2.domain.Tarea;
import Punto2.infraestructure.repositoryImpl.EmpleadoRepositoryImpl;
import Punto2.infraestructure.repositoryImpl.TareaRepositoryImpl;
import Punto2.interfaces.EmpleadoRepository;
import Punto2.interfaces.TareaRepository;

import javax.swing.JOptionPane;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    private static final EmpleadoService empleadoService;
    private static final TareaService tareaService;

    static {
        EmpleadoRepository empleadoRepository = new EmpleadoRepositoryImpl();
        empleadoService = new EmpleadoServiceImpl(empleadoRepository);
        TareaRepository tareaRepository = new TareaRepositoryImpl();
        tareaService = new TareaServiceImpl(tareaRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                    "1. Registrar nuevo empleado\n" +
                            "2. Actualizar empleado\n" +
                            "3. Asignar nueva tarea\n" +
                            "4. Eliminar tarea\n" +
                            "5. Mostrar lista de empleados\n" +
                            "6. Mostrar tareas de un empleado\n" +
                            "7. Actualizar estado de tarea\n" +
                            "8. Buscar empleados por departamento\n" +
                            "9. Calcular salario total por departamento\n" +
                            "10. Salir\n" +
                            "Seleccione una opción:"
            );

            if (opcion == null) {
                salir = true;
                continue;
            }

            switch (opcion) {
                case "1":
                    registrarEmpleado();
                    break;
                case "2":
                    actualizarEmpleado();
                    break;
                case "3":
                    asignarTarea();
                    break;
                case "4":
                    eliminarTarea();
                    break;
                case "5":
                    listarEmpleados();
                    break;
                case "6":
                    listarTareasEmpleado();
                    break;
                case "7":
                    actualizarEstadoTarea();
                    break;
                case "8":
                    buscarEmpleadosPorDepartamento();
                    break;
                case "9":
                    calcularSalarioTotal();
                    break;
                case "10":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private static void registrarEmpleado() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del empleado:");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del empleado:");
        String edadInput = JOptionPane.showInputDialog("Ingrese la edad del empleado:");
        int edad = Integer.parseInt(edadInput);
        String cargo = JOptionPane.showInputDialog("Ingrese el cargo del empleado:");
        String departamento = JOptionPane.showInputDialog("Ingrese el departamento del empleado:");
        String salarioInput = JOptionPane.showInputDialog("Ingrese el salario del empleado:");
        double salario = Double.parseDouble(salarioInput);

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setEdad(edad);
        empleado.setCargo(cargo);
        empleado.setDepartamento(departamento);
        empleado.setSalario(salario);

        empleadoService.save(empleado);
        JOptionPane.showMessageDialog(null, "Empleado registrado exitosamente.");
    }

    private static void actualizarEmpleado() {
        String idInput = JOptionPane.showInputDialog("Ingrese el ID del empleado a actualizar:");
        int id = Integer.parseInt(idInput);

        Empleado empleado = empleadoService.findById(id);
        if (empleado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el empleado con ID " + id);
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del empleado (dejar en blanco para no cambiar):");
        if (!nombre.isEmpty()) {
            empleado.setNombre(nombre);
        }

        String apellido = JOptionPane.showInputDialog("Ingrese el nuevo apellido del empleado (dejar en blanco para no cambiar):");
        if (!apellido.isEmpty()) {
            empleado.setApellido(apellido);
        }

        String edadInput = JOptionPane.showInputDialog("Ingrese la nueva edad del empleado (dejar en blanco para no cambiar):");
        if (!edadInput.isEmpty()) {
            int edad = Integer.parseInt(edadInput);
            empleado.setEdad(edad);
        }

        String cargo = JOptionPane.showInputDialog("Ingrese el nuevo cargo del empleado (dejar en blanco para no cambiar):");
        if (!cargo.isEmpty()) {
            empleado.setCargo(cargo);
        }

        String departamento = JOptionPane.showInputDialog("Ingrese el nuevo departamento del empleado (dejar en blanco para no cambiar):");
        if (!departamento.isEmpty()) {
            empleado.setDepartamento(departamento);
        }

        String salarioInput = JOptionPane.showInputDialog("Ingrese el nuevo salario del empleado (dejar en blanco para no cambiar):");
        if (!salarioInput.isEmpty()) {
            double salario = Double.parseDouble(salarioInput);
            empleado.setSalario(salario);
        }

        empleadoService.update(empleado);
        JOptionPane.showMessageDialog(null, "Empleado actualizado exitosamente.");
    }

    private static void asignarTarea() {
        String idEmpleadoInput = JOptionPane.showInputDialog("Ingrese el ID del empleado:");
        int idEmpleado = Integer.parseInt(idEmpleadoInput);

        Empleado empleado = empleadoService.findById(idEmpleado);
        if (empleado == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el empleado con ID " + idEmpleado);
            return;
        }

        String titulo = JOptionPane.showInputDialog("Ingrese el título de la tarea:");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripción de la tarea:");

        LocalDate fechaInicio = null;
        while (fechaInicio == null) {
            String fechaInicioInput = JOptionPane.showInputDialog("Ingrese la fecha de inicio (YYYY-MM-DD):");
            try {
                fechaInicio = LocalDate.parse(fechaInicioInput, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Intente de nuevo.");
            }
        }

        LocalDate fechaFin = null;
        while (fechaFin == null) {
            String fechaFinInput = JOptionPane.showInputDialog("Ingrese la fecha de fin (YYYY-MM-DD):");
            try {
                fechaFin = LocalDate.parse(fechaFinInput, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Intente de nuevo.");
            }
        }

        String estado = JOptionPane.showInputDialog("Ingrese el estado de la tarea:");

        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tarea.setFechaInicio(fechaInicio);
        tarea.setFechaFin(fechaFin);
        tarea.setEstado(estado);
        tarea.setEmpleado(empleado);

        tareaService.save(tarea);
        JOptionPane.showMessageDialog(null, "Tarea asignada exitosamente.");
    }

    private static void eliminarTarea() {
        String idTareaInput = JOptionPane.showInputDialog("Ingrese el ID de la tarea a eliminar:");
        int idTarea = Integer.parseInt(idTareaInput);

        Tarea tarea = tareaService.findById(idTarea);
        if (tarea == null) {
            JOptionPane.showMessageDialog(null, "No se encontró la tarea con ID " + idTarea);
            return;
        }

        tareaService.delete(idTarea);
        JOptionPane.showMessageDialog(null, "Tarea eliminada exitosamente.");
    }

    private static void listarEmpleados() {
        List<Empleado> empleados = empleadoService.findAll();
        if (empleados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay empleados registrados.");
        } else {
            StringBuilder mensaje = new StringBuilder("Listado de empleados:\n");
            for (Empleado empleado : empleados) {
                mensaje.append(empleado).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    private static void listarTareasEmpleado() {
        String idEmpleadoInput = JOptionPane.showInputDialog("Ingrese el ID del empleado:");
        int idEmpleado = Integer.parseInt(idEmpleadoInput);

        List<Tarea> tareas = tareaService.findByEmpleado(idEmpleado);
        if (tareas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay tareas asignadas al empleado con ID " + idEmpleado);
        } else {
            StringBuilder mensaje = new StringBuilder("Listado de tareas:\n");
            for (Tarea tarea : tareas) {
                mensaje.append(tarea).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    private static void actualizarEstadoTarea() {
        String idTareaInput = JOptionPane.showInputDialog("Ingrese el ID de la tarea:");
        int idTarea = Integer.parseInt(idTareaInput);

        Tarea tarea = tareaService.findById(idTarea);
        if (tarea == null) {
            JOptionPane.showMessageDialog(null, "No se encontró la tarea con ID " + idTarea);
            return;
        }

        String nuevoEstado = JOptionPane.showInputDialog("Ingrese el nuevo estado de la tarea:");
        tarea.setEstado(nuevoEstado);

        tareaService.update(tarea);
        JOptionPane.showMessageDialog(null, "Estado de la tarea actualizado exitosamente.");
    }

    private static void buscarEmpleadosPorDepartamento() {
        String departamento = JOptionPane.showInputDialog("Ingrese el departamento a buscar:");
        List<Empleado> empleados = empleadoService.findByDepartamento(departamento);
        if (empleados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron empleados en el departamento " + departamento);
        } else {
            StringBuilder mensaje = new StringBuilder("Empleados en el departamento " + departamento + ":\n");
            for (Empleado empleado : empleados) {
                mensaje.append(empleado).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    private static void calcularSalarioTotal() {
        String departamento = JOptionPane.showInputDialog("Ingrese el departamento para calcular el salario total:");
        double salarioTotal = empleadoService.calcularSalarioTotal(departamento);
        JOptionPane.showMessageDialog(null, "El salario total en el departamento " + departamento + " es: " + salarioTotal);
    }
}