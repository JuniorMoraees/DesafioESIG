package br.com.esig.desafioesig.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String cidade;
    @Column
    private String email;
    @Column
    private String cep;
    @Column
    private String enderco;
    @Column
    private String pais;
    @Column
    private String usuario;
    @Column
    private String telefone;
    @Column
    private String data_nascimento;
    @ManyToOne
    private Cargo cargo_id;

    @Transient
    private Salario salario;

    public double calcularSalario() {
        if (this.cargo_id != null) {
            double salarioBase = this.cargo_id.getId();
            double salarioCalculado = salarioBase;

            switch (this.cargo_id.getId()) {
                case 1:
                    salarioCalculado = getValorBeneficio(1);
                    break;
                case 2:
                    salarioCalculado = getValorBeneficio(2);
                    salarioCalculado -= getValorDesconto(8);
                    salarioCalculado -= getValorDesconto(9);
                    break;
                case 3:
                    salarioCalculado = getValorBeneficio(3);
                    salarioCalculado -= getValorDesconto(8);
                    salarioCalculado -= getValorDesconto(9);
                    break;
                case 4:
                    salarioCalculado = getValorBeneficio(4);
                    salarioCalculado += getValorBeneficio(6);
                    salarioCalculado -= getValorDesconto(8);
                    salarioCalculado -= getValorDesconto(9);
                    break;
                case 5:
                    salarioCalculado = getValorBeneficio(5);
                    salarioCalculado += getValorBeneficio(7);
                    salarioCalculado -= getValorDesconto(8);
                    salarioCalculado -= getValorDesconto(9);
                    break;
            }

            return salarioCalculado;
        } else {
            return 0.0;
        }
    }

    private double getValorBeneficio(int salarioId) {
        switch (salarioId) {
            case 1:
                return 400.0;
            case 2:
                return 1000.0;
            case 3:
                return 2500.0;
            case 4:
                return 5000.0;
            case 5:
                return 6500.0;
            case 6:
                return 500.0;
            case 7:
                return 1000.0;
            default:
                return 0.0;
        }
    }

    private double getValorDesconto(int salarioId) {
        switch (salarioId) {
            case 8:
                return 350.0;
            case 9:
                return 11.0;
            default:
                return 0.0;
        }
    }
}
