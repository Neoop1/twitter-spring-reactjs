import {makeStyles, Theme} from "@material-ui/core";

export const useChangePhoneModalStyles = makeStyles((theme: Theme) => ({
    dialog: {
        "& .MuiDialogTitle-root": {
            padding: "5px 15px",
            marginBottom: 0,
            borderBottom: "1px solid rgb(239, 243, 244)"
        },
        "& .MuiDialogContent-root": {
            paddingTop: 10,
        },
    },
    content: {
        width: 598,
        height: 650,
        position: "relative",
        overflowX: "hidden",
        padding: "0px 32px",
    },
    logoIcon: {
        margin: "0 auto",
        width: 53,
        "& svg": {
            height: "2.30em",
            color: "rgb(29, 161, 245)",
        },
    },
    title: {
        padding: "16px 0px",
        fontSize: 23,
        fontWeight: 700,
        lineHeight: "28px",
        color: theme.palette.text.primary,
    },
    text: {
        marginBottom: 20,
        fontSize: 15,
        fontWeight: 400,
        lineHeight: "20px",
    },
    textSecondary: {
        color: theme.palette.text.secondary,
    },
    textPrimary: {
        width: 504,
        color: theme.palette.text.primary,
    },
    infoWrapper: {
        marginTop: 38,
        display: "flex",
        justifyContent: "space-between",
    },
    selectWrapper: {
        marginBottom: 20,
        "& .MuiFormControl-root": {
            width: "100%"
        },
    },
    link: {
        textDecoration: "none",
        color: theme.palette.primary.main,
        cursor: "pointer",
        "&:hover": {
            textDecoration: "underline",
        }
    },
    footer: {
        width: "100%",
        bottom: 0,
        position: "absolute",
        paddingRight: 64,
        paddingBottom: 36
    },
}));