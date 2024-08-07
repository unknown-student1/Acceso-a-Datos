package Ejercicio2;

import java.util.Date;

public class Empleado {
    private int empNo;
    private String apellido;
    private String oficio;
    private Integer dir;  // Puede ser nulo
    private Date fechaAlt;
    private float salario;
    private Float comision;  // Puede ser nulo
    private int deptNo;

    public Empleado(int empNo, String apellido, String oficio, Integer dir, Date fechaAlt, float salario, Float comision, int deptNo) {
        this.empNo = empNo;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fechaAlt = fechaAlt;
        this.salario = salario;
        this.comision = comision;
        this.deptNo = deptNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Integer getDir() {
        return dir;
    }

    public void setDir(Integer dir) {
        this.dir = dir;
    }

    public Date getFechaAlt() {
        return fechaAlt;
    }

    public void setFechaAlt(Date fechaAlt) {
        this.fechaAlt = fechaAlt;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        if (salario >= 0) {
            this.salario = salario;
        } else {
            throw new IllegalArgumentException("El salario no puede ser negativo.");
        }
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "empNo=" + empNo +
                ", apellido='" + apellido + '\'' +
                ", oficio='" + oficio + '\'' +
                ", dir=" + (dir != null ? dir : "Sin director") +
                ", fechaAlt=" + fechaAlt +
                ", salario=" + salario +
                ", comision=" + (comision != null ? comision : "Sin comisión") +
                ", deptNo=" + deptNo +
                '}';
    }
}
