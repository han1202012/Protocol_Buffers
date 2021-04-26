package kim.hsl.protobuf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tutorial.protos.AddressBook
import com.example.tutorial.protos.Person

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 创建 Person.PhoneNumber.Builder 对象
        var phoneNumber1Builder: Person.PhoneNumber.Builder =
            Person.PhoneNumber.newBuilder().
                setNumber("666")

        // 创建 Person.Builder 对象
        var person1Builder: Person.Builder =
            Person.newBuilder().
                setName("Tom").
                setId(0).
                addPhones(phoneNumber1Builder)


        // 创建 Person.PhoneNumber.Builder 对象
        var phoneNumber2Builder: Person.PhoneNumber.Builder =
            Person.PhoneNumber.newBuilder().
                setNumber("888")

        // 创建 Person.Builder 对象
        var person2Builder: Person.Builder =
            Person.newBuilder().
                setName("Jerry").
                setId(1).
                addPhones(phoneNumber2Builder)


        // 使用 newBuilder 方法创建 AddressBook.Builder 对象
        var addressBookBuilder: AddressBook.Builder =
            AddressBook.newBuilder().
                addPeople(person1Builder).
                addPeople(person2Builder)

        // 将上述各个 Builder 拼装完毕后 , 最后调用 build
        // 即可得到最终对象
        var addressBook: AddressBook = addressBookBuilder.build()

    }

}