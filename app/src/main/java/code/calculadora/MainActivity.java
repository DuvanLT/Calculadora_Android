package code.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.jetbrains.annotations.TestOnly;

public class MainActivity extends AppCompatActivity {
    Operar opr = new Operar();
    private TextView resultado,last;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.resultado);
        last = findViewById(R.id.last);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void calcular(View view){

        if(view.getId() != R.id.igual && view.getId() != R.id.parentesis && view.getId() != R.id.eliminar){
            Button button = (Button) view;
            String textButton = button.getText().toString();
            opr.setValor(opr.getValor() + textButton);
        }

        if(view.getId() == R.id.clear){
            opr.setValor("");
            resultado.setText(opr.getValor());
        }

        if(view.getId() == R.id.eliminar){
            try {
                opr.setValor(opr.getValor().substring(0,opr.getValor().length() - 1));
                resultado.setText(opr.getValor());
            }catch (Exception e){
                Toast.makeText(this, "No hay expresion a eliminar", Toast.LENGTH_SHORT).show();
            }

        }

        if (view.getId() == R.id.parentesis) {
            opr.setLado_parentesis(opr.lado_parentesis + 1);
            if(opr.getLado_parentesis()%2 == 0){
                opr.setValor(opr.getValor() + ")");
            }else{
                opr.setValor(opr.getValor() + "*(");
            }

        }
        resultado.setText(opr.getValor());

        if(view.getId() == R.id.igual){
            try {
            String valor = opr.getValor();
            double operado = new ExpressionBuilder(valor).build().evaluate();

            if(valor.contains("/") || valor.contains(".")){
                String decimal = String.valueOf(operado);
                resultado.setText(decimal);
                last.setText(valor + "=" + decimal);
            } else {
                int entero = (int) operado;
                resultado.setText(String.valueOf(entero));
                last.setText(valor + "=" + entero);
            }
        }catch (Exception e){
            Toast.makeText(this, "Formato invalido", Toast.LENGTH_SHORT).show();
        }

        }




        }

    }
