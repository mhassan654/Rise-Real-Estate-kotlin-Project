package com.saavatech.riserealestate.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.riserealestate.R
import com.saavatech.riserealestate.ui.theme.ButtonBgOne
import com.saavatech.riserealestate.ui.theme.GreenOne
import com.saavatech.riserealestate.ui.theme.TextColorBold
import com.saavatech.riserealestate.ui.theme.TextColorOne
import com.saavatech.riserealestate.ui.theme.outlineInputunFocusedColor

@Composable
fun NormalTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = TextColorOne,
        textAlign = TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value: String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 20.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun CustomOutlinedTextField(painterResource: Painter, lableValue: String){
    val text = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier= Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(1.dp)),
        label = { Text(text =lableValue) } ,
        value = text.value,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = ButtonBgOne,
            focusedBorderColor = Color.Transparent,
            focusedLabelColor = TextColorBold,
            unfocusedContainerColor = Color.LightGray
        ),

        keyboardOptions = KeyboardOptions.Default,
        onValueChange ={
            text.value=it
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource, contentDescription = "profile icon")
        }
    )
}
@Composable
fun CustomOutlinedPasswordTextField(lableValue: String){
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier= Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(1.dp)),
        label = { Text(text =lableValue) } ,
        value = password.value,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            //        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = ButtonBgOne,
            focusedBorderColor = colorResource(id = R.color.colorPrimary),
            unfocusedContainerColor = Color.LightGray
//            focusedLabelColor = LightPrimaryContainer,
        ),

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange ={
            password.value=it
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.lock), contentDescription = "profile icon")
        },
        trailingIcon = {
            val iconImage = if (passwordVisibility.value)
                Icons.Filled.Visibility
            else
                Icons.Filled.VisibilityOff

            val description = if (passwordVisibility.value)
                "Hide Password"
            else "Show password"

            IconButton(onClick = {
                passwordVisibility.value = !passwordVisibility.value
            }) {

                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Composable
fun ButtonTextComponent(value: String, clickAction: ()->Unit){
    TextButton(
        modifier = Modifier
            .width(150.dp)
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(GreenOne),
        shape = RoundedCornerShape(8),
        onClick = { clickAction.invoke() }
    ) {
        Text(text = value)
    }
}

@Composable
fun ButtonIconComponent(value: String){
    IconButton(
        modifier = Modifier
            .width(150.dp)
            .heightIn(40.dp),
//        contentPadding = PaddingValues(),
//        colors = ButtonDefaults.buttonColors(GreenOne),
//        shape = RoundedCornerShape(15),
        onClick = {  }
    ) {
        Text(text = value)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String?,
    icon: ImageVector,
    actionIcon: ImageVector?,
    iconClickAction: ()->Unit
){

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {

            RoundedIconButton(
                icon = icon,
                onClick = { iconClickAction.invoke() },
                contentDescription = "Add to favorites"
            )

        },
        actions = {
            if (actionIcon != null) {
                RoundedIconButton(
                    icon = actionIcon,
                    onClick = { iconClickAction.invoke() },
                    contentDescription = "Share to others"
                )
            }
        },
        title = {
            if (title != null) {
                Text(title)
            }
        }
    )
}

@Composable
fun RoundedIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = ButtonBgOne,
    contentDescription: String? = null
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .background(colorResource(id = R.color.colorGray), RoundedCornerShape(12.dp))
            .padding(horizontal = 2.dp)
            .padding(vertical = 2.dp)
            .size(30.dp)
    ) {
        Icon(
            imageVector = icon,
            tint = tint,
            contentDescription = contentDescription,
            modifier = Modifier.size(20.dp)
        )
    }
//    }
}

@Composable
fun RoundedIconTextButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.primary,
    contentDescription: String? = null,
    text: String
) {
    Row {
        TextButton(
            modifier = Modifier
                .widthIn(278.dp)
                .height(63.dp),
            contentPadding = PaddingValues(),
            colors = ButtonDefaults.buttonColors(GreenOne),
            shape = RoundedCornerShape(15),
            onClick = { onClick.invoke() }
        ) {
            Row(
                modifier=Modifier.padding(horizontal = 10.dp)
            ) {
                Icon(
                    modifier=Modifier.size(20.dp),
                    imageVector = icon,
                    contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = text, fontSize = 16.sp, fontWeight = FontWeight(600))
            }

        }

    }

//    }
}

@Composable
fun DividerTextComponent(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorResource(id = R.color.strokeColor),
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = "OR",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = colorResource(id = R.color.strokeTextColor)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = colorResource(id = R.color.strokeColor),
            thickness = 1.dp
        )

    }
}

@Composable
fun SocialButton(
    icon: Painter,
    onClick: () -> Unit,
){
    TextButton(
        modifier = Modifier
            .width(158.5.dp)
            .height(70.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            colorResource(id = R.color.socialButtonBgColor)
        ),
        shape = RoundedCornerShape(40),
        onClick = {  }
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = icon, contentDescription = null
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsPreview(){
    Column {
        CustomOutlinedPasswordTextField("sample label")
        Spacer(modifier = Modifier.height(6.dp))
        CustomOutlinedTextField(painterResource(id = R.drawable.profile), "saghsadg")
        Spacer(modifier = Modifier.height(6.dp))
        ButtonTextComponent(value = "button with text", clickAction = {})
        Spacer(modifier = Modifier.height(6.dp))

        RoundedIconTextButton(
            icon = Icons.Default.MailOutline,
            onClick = {  },
            contentDescription = "Add to favorites",
            text = "Sample text icon button"
        )
        Spacer(modifier = Modifier.height(6.dp))

        AppBar(
            "Details",
            Icons.AutoMirrored.Filled.ArrowBack,
            Icons.Filled.Share,
            iconClickAction = {
//                navController?.navigateUp()
            })
        Spacer(modifier = Modifier.height(6.dp))
        DividerTextComponent()

        SocialButton(
           icon= painterResource(id = R.drawable.google_48),
            onClick = {}
        )
    }

}