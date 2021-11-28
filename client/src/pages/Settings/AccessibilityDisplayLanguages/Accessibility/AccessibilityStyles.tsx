import {makeStyles, Theme} from "@material-ui/core";

export const useAccessibilityStyles = makeStyles((theme: Theme) => ({
    infoItemWrapper: {
        padding: "12px 16px"
    },
    text: {
        color: theme.palette.text.secondary,
        fontWeight: 400,
        fontSize: 13,
        lineHeight: "16px"
    },
    title: {
        fontWeight: 800,
        lineHeight: "24px",
        fontSize: 20,
        color: theme.palette.text.primary,
    },
    infoItem: {
        paddingBottom: 12,
        fontSize: 15,
        color: theme.palette.text.primary,
        fontWeight: 400,
        lineHeight: "20px",
        "& .MuiCheckbox-root": {
            float: "right",
            marginTop: -10,
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
    accessibilityWrapper: {
        textDecoration: "none",
    },
    accessibilityLink: {
        padding: "12px 16px",
        fontSize: 15,
        color: theme.palette.text.primary,
        fontWeight: 400,
        lineHeight: "20px",
        "&:hover": {
            backgroundColor: "rgb(247, 249, 249)",
            cursor: "pointer"
        },
        "& svg": {
            marginTop: 8,
            float: "right",
            color: "rgb(83, 100, 113)",
            height: "1.4em"
        },
    },
    accessibilityInfo: {
        display: "inline-block",
        width: 200
    },
}));